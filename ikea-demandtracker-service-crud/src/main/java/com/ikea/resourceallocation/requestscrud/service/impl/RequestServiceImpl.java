package com.ikea.resourceallocation.requestscrud.service.impl;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.ikea.resourceallocation.requestscrud.constants.Grades;
import com.ikea.resourceallocation.requestscrud.conversion.EntityDtoConversion;
import com.ikea.resourceallocation.requestscrud.customexception.CantWithdrawException;
import com.ikea.resourceallocation.requestscrud.customexception.RequestNotFoundException;
import com.ikea.resourceallocation.requestscrud.customexception.RequestNotUpdateException;
import com.ikea.resourceallocation.requestscrud.dto.RequestCreationDto;
import com.ikea.resourceallocation.requestscrud.dto.RequestCreationResponseDto;
import com.ikea.resourceallocation.requestscrud.dto.RequestForCopyDto;
import com.ikea.resourceallocation.requestscrud.dto.RequestsForListPageDto;
import com.ikea.resourceallocation.requestscrud.dto.UserRole;
import com.ikea.resourceallocation.requestscrud.messages.CrudMessages;
import com.ikea.resourceallocation.requestscrud.model.Cluster;
import com.ikea.resourceallocation.requestscrud.model.Request;
import com.ikea.resourceallocation.requestscrud.model.SubClusters;
import com.ikea.resourceallocation.requestscrud.model.search.RequestFilterCriteria;
import com.ikea.resourceallocation.requestscrud.repo.ClusterRepo;
import com.ikea.resourceallocation.requestscrud.repo.RequestCrudRepo;
import com.ikea.resourceallocation.requestscrud.repo.SubClusterRepo;
import com.ikea.resourceallocation.requestscrud.service.RequestService;

@Transactional
public class RequestServiceImpl implements RequestService {

	private final EntityManager entityManager;
	private final CriteriaBuilder criteriaBuilder;
	private static ClusterRepo clusterRepo = null;
	private static SubClusterRepo subClusterRepo = null;

	public RequestServiceImpl(EntityManager entityManager, ClusterRepo clusterRepo, SubClusterRepo subClusterRepo) {
		this.entityManager = entityManager;
		this.criteriaBuilder = entityManager.getCriteriaBuilder();
		RequestServiceImpl.clusterRepo = clusterRepo;
		RequestServiceImpl.subClusterRepo = subClusterRepo;
	}

	@Autowired
	private RequestCrudRepo requestCrudRepo;

	@Override
	public RequestCreationResponseDto createRequest(RequestCreationDto requestCreationDto) {
		ModelMapper modelMapper = new ModelMapper();
		Request req = new Request();
		EntityDtoConversion convert = new EntityDtoConversion();
		req = modelMapper.map(requestCreationDto, Request.class);
		req = requestCrudRepo.save(req);
		RequestCreationResponseDto request = convert.EntityToDtoConvertion(req, CrudMessages.message.get("SuccessMsg"));
		return request;
	}

	@Override
	public List<RequestsForListPageDto> getAllRequests() {
		ModelMapper modelMapper = new ModelMapper();
		List<Request> requests = requestCrudRepo.findAll();
//		List<RequestsForListPageDto> reqDto = requests.stream().map(
//				request -> modelMapper.map(request, RequestsForListPageDto.RequestsForListPageDtoBuilder.class).build())
//				.collect(Collectors.toList());
		List<RequestsForListPageDto> reqDto = requestToDtoConvertion(requests);
		return reqDto;
	}

	@Override
	public RequestCreationResponseDto getRequestById(String reqId) {
		Optional<Request> request = requestCrudRepo.findById(reqId);
		if (!request.isPresent()) {
			throw new RequestNotFoundException(CrudMessages.message.get("RequestNotFoundMsg"));
		}

		Cluster cluster = getClusterName(request.get().getClusterId());
		if (cluster != null) {
			request.get().setClusterName((cluster.getCluster()));
		}

		SubClusters subClusters = getSubClusterName(request.get().getSubClusterId());

		if (subClusters != null) {
			request.get().setSubClusterName(subClusters.getSubCluster());
		}

		request.get().setGrade(Grades.getGradeById(request.get().getGradeId()));

		EntityDtoConversion convert = new EntityDtoConversion();
		RequestCreationResponseDto requestCreationResponseDto = convert.EntityToDtoConvertion(request.get(),
				request.get().getReqId() + " " + CrudMessages.message.get("RequestFoundMsg"));

		return requestCreationResponseDto;
	}

	public static Cluster getClusterName(String clus) {
		return clusterRepo.findClusterByClusterId(clus);
	}

	public static SubClusters getSubClusterName(String subClus) {
		return subClusterRepo.findSubClusterBySubClusId(subClus);
	}

	@Override
	public RequestCreationResponseDto updateRequest(RequestCreationDto requestCreationDto) {
		ModelMapper modelMapper = new ModelMapper();
		Request req = new Request();
		req = modelMapper.map(requestCreationDto, Request.class);
		Optional<Request> reque = requestCrudRepo.findById(requestCreationDto.getReqId());
		if (reque.isPresent()) {
			if ((reque.get().getSoNumber() != null )
					&& requestCreationDto.getStatus().equals("withdraw")) {
				throw new CantWithdrawException(CrudMessages.message.get("CantWithDrawException"));
			} else {
				Request savedReq = requestCrudRepo.save(req);
				EntityDtoConversion convert = new EntityDtoConversion();
				RequestCreationResponseDto updatedReq = convert.EntityToDtoConvertion(savedReq,
						savedReq.getReqId() + " " + CrudMessages.message.get("RequestUpdatedMsg"));
				return updatedReq;
			}

		}
		throw new RequestNotUpdateException(CrudMessages.message.get("RequestNotUpdatedMsg"));
	}

	@Override
	public List<RequestsForListPageDto> getAllRequestsWithFilters(RequestFilterCriteria filterCriteria) {
		CriteriaQuery<Request> criteriaQuery = criteriaBuilder.createQuery(Request.class);
		Root<Request> root = criteriaQuery.from(Request.class);
		Predicate predicate = getPredicate(filterCriteria, root);
		criteriaQuery.select(root).where(predicate);

		List<RequestsForListPageDto> requestLists = requestToDtoConvertion(
				entityManager.createQuery(criteriaQuery).getResultList());

		if (UserRole.getInstance().getReqOrPmo().equals("PMO")) {
			requestLists = requestLists.stream().filter(req -> !req.getStatus().equals("withdraw"))
					.collect(Collectors.toList());
		}

		if (UserRole.getInstance().getReqOrPmo().equals("REQ") || UserRole.getInstance().getReqOrPmo().equals("PMO")) {
			return requestLists;
		}
		return null;

	}

	private List<RequestsForListPageDto> requestToDtoConvertion(List<Request> resultList) {

		List<Request> requests = new ArrayList<>();

		requests = resultList.stream()
				.map(req -> ClusterAndSubClusterSet(req, req.getClusterId(), req.getSubClusterId()))
				.collect(Collectors.toList());

		System.out.println(requests);

//		List<RequestsForListPageDto> reqDto = requests.stream().map(
//				request -> modelMapper.map(request, RequestsForListPageDto.RequestsForListPageDtoBuilder.class).build())
//				.collect(Collectors.toList());

		List<RequestsForListPageDto> reqDto = requests.stream()
				.map(request -> RequestsForListPageDto.builder().reqId(request.getReqId())
						.clusterName(request.getClusterName()).subClusterName(request.getSubClusterName())
						.gradeId(request.getGradeId()).ownerName(request.getOwnerName())
						.techStack(request.getTechStack()).lastModifiedDate(dateConverter(request.getUpdatedAt()))
						.status(request.getStatus()).build())
				.collect(Collectors.toList());

		return reqDto;

	}

	private String dateConverter(Date date) {
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		return formatter.format(date);
	}

	public static Request ClusterAndSubClusterSet(Request req, String clusterId, String SubClusterId) {
		Cluster clus = getClusterName(req.getClusterId());
		SubClusters sub = getSubClusterName(req.getSubClusterId());
		if (clus != null) {
			req.setClusterName((clus.getCluster()));
		}
		if (sub != null) {
			req.setSubClusterName(sub.getSubCluster());
		}
		return req;
	}

	private Predicate getPredicate(RequestFilterCriteria filterCriteria, Root<Request> root) {

		List<Predicate> predicates = new ArrayList<>();
		Predicate searchPredicate = null;
		List<Predicate> statusPredicate = null;
		List<Predicate> gradesPredicate = null;
		List<Predicate> clusPredicate = null;
		List<Predicate> subClusPredicate = null;

		if (Objects.nonNull(filterCriteria.getSearch()) && !filterCriteria.getSearch().isEmpty()) {

			searchPredicate = criteriaBuilder.like(root.get("reqId"), filterCriteria.getSearch() + "%");

			predicates.add(criteriaBuilder.or(searchPredicate));

		}

		if (Objects.nonNull(filterCriteria.getStatus()) && !filterCriteria.getStatus().isEmpty()) {

			statusPredicate = filterCriteria.getStatus().stream()
					.map(status -> criteriaBuilder.or(criteriaBuilder.like(root.get("status"), "%" + status + "%")))
					.collect(Collectors.toList());

			predicates.add(criteriaBuilder.or(statusPredicate.toArray(new Predicate[statusPredicate.size()])));

		}

		if (Objects.nonNull(filterCriteria.getGrade()) && !filterCriteria.getGrade().isEmpty()) {
			gradesPredicate = filterCriteria.getGrade().stream()
					.map(grade -> criteriaBuilder.or(criteriaBuilder.equal(root.get("gradeId"), grade)))
					.collect(Collectors.toList());

			predicates.add(criteriaBuilder.or(gradesPredicate.toArray(new Predicate[gradesPredicate.size()])));

		}

		if (Objects.nonNull(filterCriteria.getClus()) && !filterCriteria.getClus().isEmpty()) {
			clusPredicate = filterCriteria.getClus().stream()
					.map(clus -> criteriaBuilder.or(criteriaBuilder.equal(root.get("clusterId"), clus)))
					.collect(Collectors.toList());

			predicates.add(criteriaBuilder.or(clusPredicate.toArray(new Predicate[clusPredicate.size()])));

		}

		if (Objects.nonNull(filterCriteria.getSubCluster()) && !filterCriteria.getSubCluster().isEmpty()) {
			subClusPredicate = filterCriteria.getSubCluster().stream()
					.map(subClus -> criteriaBuilder.or(criteriaBuilder.equal(root.get("subClusterId"), subClus)))
					.collect(Collectors.toList());

//			predicates.addAll(gradesPredicate.stream().map(gradePred -> criteriaBuilder.or(gradePred)).toList());
			predicates.add(criteriaBuilder.or(subClusPredicate.toArray(new Predicate[subClusPredicate.size()])));

		}

		/*
		 * 
		 * if (Objects.nonNull(filterCriteria.getClus())) {
		 * predicates.add(criteriaBuilder.equal(root.get("clusterId"),
		 * filterCriteria.getClus())); }
		 * 
		 * if (Objects.nonNull(filterCriteria.getSubCluster())) {
		 * predicates.add(criteriaBuilder.equal(root.get("subClusterId"),
		 * filterCriteria.getSubCluster())); }
		 */

//		if (Objects.nonNull(filterCriteria.getStartDate())) {
//			predicates.add(criteriaBuilder.between(root.get("reqCreatedDate"), filterCriteria.getStartDate(),
//					filterCriteria.getEndDate()));
//		}

		return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
	}

	@Override
	public RequestForCopyDto getDataForCopyOptionById(String reqId) {
		Optional<Request> request = requestCrudRepo.findById(reqId);
		if (!request.isPresent()) {
			throw new RequestNotFoundException(CrudMessages.message.get("RequestNotFoundMsg"));
		}
		request.get().setGrade(Grades.getGradeById(request.get().getGradeId()));
		RequestForCopyDto copyDto = RequestForCopyDto.builder().grade(request.get().getGradeId())
				.selectedGrade(request.get().getGrade()).stack(request.get().getTechStack())
				.skill(request.get().getCoreSkill()).jd(request.get().getSkillDetails()).build();
		return copyDto;
	}
}

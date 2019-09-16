package com.cdf.factory.dao.mongodb;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.cdf.factory.entity.Namespace;

@Service
public class NamespaceDao {

	@Autowired
	MongoTemplate mongoTemplate;

	public int addNamespace(Namespace namespace) {
		mongoTemplate.save(namespace);
		return 1;
	}

	public List<Namespace> queryNamespace(String name, String code, Integer offset, Integer limit) {
		Query query = new Query();
		if (StringUtils.isNotEmpty(name)) {
			query.addCriteria(Criteria.where("name").is(name));
		}
		if (StringUtils.isNotEmpty(code)) {
			query.addCriteria(Criteria.where("code").is(code));
		}
		query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "creatTime")));
		int skip = (offset - 1) * limit;
		query.skip(skip);// 从那条记录开始
		query.limit(limit);// 取多少条记录
		List<Namespace> list = mongoTemplate.find(query, Namespace.class);
		return list;
	}

	public int updateNamespace(Namespace namespace) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(namespace.getId()));
		Update update = new Update();
		if (StringUtils.isNotEmpty(namespace.getName())) {
			update.set("name", namespace.getName());
		}
		if (StringUtils.isNotEmpty(namespace.getCode())) {
			update.set("code", namespace.getCode());
		}
		if (StringUtils.isNotEmpty(namespace.getDescription())) {
			update.set("description", namespace.getDescription());
		}
		mongoTemplate.updateFirst(query, update, Namespace.class);
		return 1;
	}	

	public int delNamespace(String id) {
		Query query = new Query(Criteria.where("id").is(id));
		mongoTemplate.remove(query, Namespace.class);
		return 1;
	}

	public Namespace queryNamespaceById(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		Namespace namespace = mongoTemplate.findOne(query, Namespace.class);
		return namespace;
	}

}

package com.jamesknights.common.user.model.impl;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserImpl.class)
public abstract class UserImpl_ {

	public static volatile SingularAttribute<UserImpl, Long> uId;
	public static volatile SingularAttribute<UserImpl, String> forename;
	public static volatile SingularAttribute<UserImpl, String> emailAddress;
	public static volatile SingularAttribute<UserImpl, String> password;
	public static volatile SingularAttribute<UserImpl, LocalDateTime> created;
	public static volatile SingularAttribute<UserImpl, Boolean> isEnabled;
	public static volatile SingularAttribute<UserImpl, String> lastname;

}


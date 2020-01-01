package com.sqlite.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

/**
 * Builder for few basic database conditions.
 *
 * @author t.matras
 * @since 11. 3. 2015
 */
public abstract class BasePredicateBuilder<T> {
    
    abstract public BasePredicateBuilder getConditions(Root<T> root, CriteriaBuilder cb, Object filter);
    public Specification<T> toSpec(Object filter){
        return new Specification<T>() {

            @Override
            public Predicate toPredicate(Root<T> root,
                    CriteriaQuery<?> query, CriteriaBuilder builder) {
                criteriaBuilder = builder;
                // TODO Auto-generated method stub
                List<Predicate> predicates = getConditions(root, builder, filter).getPredicateList();
                
                return builder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
           
        };
    }

    public BasePredicateBuilder() {
        super();
    }
    /**
     * Returns string like condition which is open on both sides.
     *
     * @return like '%value%' condition
     */
    public static Predicate prepareBothSideLikeCondition(CriteriaBuilder builder, Path<String> searchedField, String requestedValue) {
        return builder.like(searchedField, "%" + requestedValue + "%");
    }

    /**
     * Return number between condition which is open according to provided values.
     * Example: a >= value <= b
     */
    public static Predicate prepareNumberBetweenCondition(CriteriaBuilder builder, Path<? extends Number> searchedField, Number from, Number to) {
        Predicate condition = null;
        if (from != null && to != null) {
            condition = builder.and(builder.ge(searchedField, from), builder.le(searchedField, to));
        }

        if (from == null) {
            condition = builder.le(searchedField, to);
        }

        if (to == null) {
            condition = builder.ge(searchedField, from);
        }
        return condition;
    }
    
    protected List<Predicate> predicateList = new ArrayList<>();
    protected CriteriaBuilder criteriaBuilder;
   
    /**
     * Create job predicate list builder
     */
    public BasePredicateBuilder(CriteriaBuilder cb) {
        this.predicateList = new ArrayList<>();
        this.criteriaBuilder = cb;
    }

    public List<Predicate> getPredicateList() {
        return predicateList;
    }

    /**
     * Compare if first argument >= second argument.
     *
     * @see javax.persistence.criteria.CriteriaBuilder
     *
     * @param a value A
     * @param b value B
     * @return this
     */
    public BasePredicateBuilder greaterEqual(Path<? extends Number> a, Number b) {
        if (b != null) predicateList.add(criteriaBuilder.ge(a, b));
        return this;
    }

    /**
     * Compare if first argument <= second argument.
     *
     * @see javax.persistence.criteria.CriteriaBuilder
     *
     * @param a value A
     * @param b value B
     * @return this
     */
    public BasePredicateBuilder lowerEqual(Path<? extends Number> a, Number b) {
        if (b != null) predicateList.add(criteriaBuilder.le(a, b));
        return this;
    }

    /**
     * Compare if first argument equals second argument.
     *
     * @see javax.persistence.criteria.CriteriaBuilder
     *
     * @param a value A
     * @param b value B
     * @return this
     */
    public BasePredicateBuilder equal(Path<?> a, Object b) {
        if (b != null) predicateList.add(criteriaBuilder.equal(a, b));
        return this;
    }
    
    public Predicate notIn(CriteriaBuilder builder, Path<?> a, Collection<?> b) {
        return builder.not(a.in(b));        
    }
    
    public Predicate isNull(Path<?> a) {
        return criteriaBuilder.isNull(a);        
    }
    
    public BasePredicateBuilder or(List<Predicate> predicates) {
        predicateList.add(criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()])));
        return this;
    }

    /**
     * Compare if first argument not equals second argument.
     *
     * @see javax.persistence.criteria.CriteriaBuilder
     *
     * @param a value A
     * @param b value B
     * @return this
     */
    public BasePredicateBuilder notEqual(Path<?> a, Object b) {
        if (b != null) predicateList.add(criteriaBuilder.notEqual(a, b));
        return this;
    }

    /**
     * Database condition: 'first argument' like 'second argument'.
     *
     * @see javax.persistence.criteria.CriteriaBuilder
     *
     * @param a value A
     * @param b value B
     * @return this
     */
    public BasePredicateBuilder like(Path<String> a, String b) {
        if (b != null && !b.isEmpty()) predicateList.add(criteriaBuilder.like(criteriaBuilder.lower(a), b.toLowerCase()));
        return this;
    }

    /**
     * Database condition: first_argument in (second_argument)
     *
     * @see javax.persistence.criteria.CriteriaBuilder
     *
     * @param a value A
     * @param b value B
     * @return this
     */
    public BasePredicateBuilder in(Path<?> a, Collection<?> b) {
        if (b != null && !b.isEmpty()) predicateList.add(a.in(b));
        return this;
    }
        

    /**
     * Database condition: 'first_argument' like '%second_argument%'
     *
     * @see javax.persistence.criteria.CriteriaBuilder
     *
     * @param a value A
     * @param b value B
     * @return this
     */
    public BasePredicateBuilder likeOpenBothSides(Path<String> a, String b) {
        if (b != null && !b.isEmpty()) predicateList.add(prepareBothSideLikeCondition(criteriaBuilder, a, b));
        return this;
    }

    /**
     * Compare if date value is between start date and end date.
     *
     * @see javax.persistence.criteria.CriteriaBuilder
     *
     * @param val checked date value
     * @param start value start date
     * @param end value end date
     * @return this
     */
    public BasePredicateBuilder dateLongTimestampBetween(Path<? extends Number> val, Number start, Number end) {
        if (start != null && end != null) predicateList.add(prepareNumberBetweenCondition(criteriaBuilder, val, start, end));
        return this;
    }

}

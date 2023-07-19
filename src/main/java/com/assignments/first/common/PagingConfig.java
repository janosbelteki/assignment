package com.assignments.first.common;

import com.assignments.first.exceptions.AssignmentException;
import com.assignments.first.repository.entities.UserEntity;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Field;
import java.util.Optional;

import static com.assignments.first.common.Constants.INVALID_PAGE_INDEX_ERROR_LABEL;
import static com.assignments.first.common.Constants.INVALID_PAGE_LIMIT_ERROR_LABEL;
import static com.assignments.first.common.Constants.ORDER_ASC;
import static com.assignments.first.common.Constants.ORDER_DESC;
import static com.assignments.first.common.Constants.USER_LIST_DEFAULT_ORDER_BY;

public class PagingConfig {
    private int pageLimit;
    private int pageIndex;
    String order;
    String orderBy;
    Boolean paging;

    public PagingConfig(int pageLimit, int pageIndex, String order, String orderBy) {
        this(pageLimit, pageIndex, order, orderBy, false);
    }

    public PagingConfig(int pageLimit, int pageIndex, String order, String orderBy, boolean paging) {
        this.pageLimit = pageLimit;
        this.pageIndex = pageIndex;
        this.order = order;
        this.orderBy = orderBy;
        this.paging = Optional.of(paging);
    }

    public int getPageLimit() {return pageLimit;}

    public int getPageIndex() {
        return pageIndex;
    }

    public String getOrder() {return order;}

    public String getOrderBy() {
        return orderBy;
    }

//    public static int getCorrectLimit(int pagingLimit) {
//        if (pagingLimit < 1 || pagingLimit > 100) {
//            throw new AssignmentException(INVALID_PAGE_LIMIT_ERROR_LABEL, HttpStatus.BAD_REQUEST);
//        }
//        return pagingLimit;
//    }
//
//    public static int getCorrectIndex(int pageIndex) {
//        if (pageIndex < 0) {
//            throw new AssignmentException(INVALID_PAGE_INDEX_ERROR_LABEL, HttpStatus.BAD_REQUEST);
//        }
//        return pageIndex;
//    }
//
//    public static String getCorrectOrder(String order) {
//        if ("DESC".equals(order.toUpperCase())) {
//            return ORDER_DESC;
//        }
//        return ORDER_ASC;
//    }
//
//    public static String getCorrectOrderBy(String orderBy, String defaultOrderBy) {
//        Field[] fields = UserEntity.class.getDeclaredFields();
//        String correctOrderBy = defaultOrderBy;
//        for (Field field : fields) {
//            String fieldName = field.getName();
//            if (orderBy.toLowerCase().equals(fieldName)) {
//                correctOrderBy = fieldName;
//            }
//        }
//        return correctOrderBy;
//    }
}

package org.company.springcloud.msvc.courses.models.external.user;

import java.util.List;

public class ListUsersByCourseRequest {

    private List<Long> listIds;

    public ListUsersByCourseRequest() {}

    public ListUsersByCourseRequest(List<Long> listIds) {
        this.listIds = listIds;
    }

    public List<Long> getListIds() {
        return listIds;
    }

    public void setListIds(List<Long> listIds) {
        this.listIds = listIds;
    }

    @Override
    public String toString() {
        return "ListUsersByCourseRequest{" +
                "listIds=" + listIds +
                '}';
    }

}

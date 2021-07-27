package com.company.models;

import java.util.HashMap;
import java.util.Map;

public class Team {
    private long firstEmployeeId;
    private long secondEmployeeId;
    private long totalJointDuration;
    private Map<Long, Long> projects = new HashMap();

    public Team(long firstEmployeeId, long secondEmployeeId, long totalJointDuration) {
        this.setFirstEmployeeId(firstEmployeeId);
        this.setSecondEmployeeId(secondEmployeeId);
        this.setTotalJointDuration(totalJointDuration);
    }

    public long getFirstEmployeeId() {
        return this.firstEmployeeId;
    }

    private void setFirstEmployeeId(long firstEmployeeId) {
        this.firstEmployeeId = firstEmployeeId;
    }

    public long getSecondEmployeeId() {
        return this.secondEmployeeId;
    }

    private void setSecondEmployeeId(long secondEmployeeId) {
        this.secondEmployeeId = secondEmployeeId;
    }

    public long getTotalJointDuration() {
        return this.totalJointDuration;
    }

    private void setTotalJointDuration(long totalJointDuration) {
        this.totalJointDuration = totalJointDuration;
    }

    public void addJointDuration(long project, long jointDuration) {
        Long current_value = jointDuration;
        if (projects.containsKey(project)) {
            current_value += projects.get(project);
        }
        projects.put(project, current_value);
        this.totalJointDuration += jointDuration;
    }

    public Map<Long, Long> getProjects() {
        return projects;
    }

    public void setProjects(long project) {
        this.projects.put(project, totalJointDuration);
    }
}

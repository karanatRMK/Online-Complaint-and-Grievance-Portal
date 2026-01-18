package com.grievance.portal.dto;

public class DashboardStats {

    private long total;
    private long pending;
    private long resolved;
    private long escalated;

    public DashboardStats(long total, long pending, long resolved, long escalated) {
        this.total = total;
        this.pending = pending;
        this.resolved = resolved;
        this.escalated = escalated;
    }

    public long getTotal() { return total; }
    public long getPending() { return pending; }
    public long getResolved() { return resolved; }
    public long getEscalated() { return escalated; }
}

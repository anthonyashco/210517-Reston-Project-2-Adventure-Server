package dev.adventure.entities;

public class Claim {
    private  int id;
    private  long date;
    private  float amount;
    private  String reason;
    private  String status;
    private  int userId;

    public Claim() {
    }
    public Claim(int id, long date, float amount, String reason, String status, int userId) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.reason = reason;
        this.status = status;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                '}';
    }
}
       
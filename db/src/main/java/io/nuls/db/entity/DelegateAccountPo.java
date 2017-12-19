package io.nuls.db.entity;

public class DelegateAccountPo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column delegate_account.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column delegate_account.address
     *
     * @mbg.generated
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column delegate_account.peer_address
     *
     * @mbg.generated
     */
    private String peerAddress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column delegate_account.deposit
     *
     * @mbg.generated
     */
    private Long deposit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column delegate_account.remark
     *
     * @mbg.generated
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column delegate_account.start_time
     *
     * @mbg.generated
     */
    private Long startTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column delegate_account.id
     *
     * @return the value of delegate_account.id
     *
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column delegate_account.id
     *
     * @param id the value for delegate_account.id
     *
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column delegate_account.address
     *
     * @return the value of delegate_account.address
     *
     * @mbg.generated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column delegate_account.address
     *
     * @param address the value for delegate_account.address
     *
     * @mbg.generated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column delegate_account.peer_address
     *
     * @return the value of delegate_account.peer_address
     *
     * @mbg.generated
     */
    public String getPeerAddress() {
        return peerAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column delegate_account.peer_address
     *
     * @param peerAddress the value for delegate_account.peer_address
     *
     * @mbg.generated
     */
    public void setPeerAddress(String peerAddress) {
        this.peerAddress = peerAddress == null ? null : peerAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column delegate_account.deposit
     *
     * @return the value of delegate_account.deposit
     *
     * @mbg.generated
     */
    public Long getDeposit() {
        return deposit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column delegate_account.deposit
     *
     * @param deposit the value for delegate_account.deposit
     *
     * @mbg.generated
     */
    public void setDeposit(Long deposit) {
        this.deposit = deposit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column delegate_account.remark
     *
     * @return the value of delegate_account.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column delegate_account.remark
     *
     * @param remark the value for delegate_account.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column delegate_account.start_time
     *
     * @return the value of delegate_account.start_time
     *
     * @mbg.generated
     */
    public Long getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column delegate_account.start_time
     *
     * @param startTime the value for delegate_account.start_time
     *
     * @mbg.generated
     */
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }
}
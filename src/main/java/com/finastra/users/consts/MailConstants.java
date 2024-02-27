package com.finastra.users.consts;

public class MailConstants {

    public final static String SUBJECT= "Email Verification";
    public final static String MAIL_HEADER= "<p> Hi, ";
    public final static String MAIL_BODY= ", </p>"+
            "<p>Your registration has been done successfully,"+"" +
            "Please sign in using the following credentials:</p>"+
            " ";
    public final static String MAIL_SALUTATION= "\" "+
            "<p> Thank you <br> Users Registration Portal Service";
    public final static String SENDER_NAME= "User Registration Portal Service";
    public final static String MODULE_APPROVED_NOTIFICATION="Module Approval Notification";
    public final static String MODULE_ENABLED_NOTIFICATION="Module Enable Notification";
    public final static String APPROVED="module has been approved";
    public final static String ENABLED="module has been enabled";
    public final static String SUCCESS= "Success!  Please, check your email for to complete your registration";
    public final static String EMAIL_VERIFIED="Email verified successfully. Now you can login to your account";
    public final static String INVALID_TOKEN="Invalid verification token";

    public final static String ACCOUNT_VERIFIED= "This account has already been verified, please, login.";
}

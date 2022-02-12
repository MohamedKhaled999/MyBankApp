package com.example.mybankapp.DS;

public class Constants {
    public static final String DATABASE_NAME = "com.example.mybankapp";
    public static final String PREFERENCE_NAME = "com.example.mybankapp";

    public static final String EMAIL = "email";

    public static class UsersTable {
        public static final String TABLE_NAME = "users";
        public static final String USER_NAME = "user_name";
        public static final String EMAIL = "email";
        public static final String AMOUNT = "amount";
 }

public class TransferTable{
    public static final String TABLE_NAME = "transfers";
    public static final String ID = "id";

    public static final String FROM = "from_user";
    public static final String TO = "to_user";
    public static final String AMOUNT = "amount";
}
}

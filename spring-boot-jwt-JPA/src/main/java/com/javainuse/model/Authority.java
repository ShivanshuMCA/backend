package com.javainuse.model;

public class Authority
{
    private String authority;
    public String getAuthority()
    {
        return authority;
    }

    public void setAuthority( String authority )
    {
        this.authority = authority;
    }

    @Override
    public String toString()
    {
        return "Authority [authority=" + authority + "]";
    }
}

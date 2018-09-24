<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<strong>Registration page</strong>
    ${message?ifExists}
<@l.login "/registration"/>
<a href="/login">Login</a>
</@c.page>
<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
<div class="mb-1"><strong>Registration page</strong></div>
    ${message?ifExists}
<@l.login "/registration" true/>
</@c.page>
<#macro login path isRegisterForm>
<form action="${path}" method="post">
    <div class="form-group row">
        <label for="username" class="col-sm-2 col-form-label">User Name :</label>
        <div class="col-sm-6">
            <input class="form-control" type="text" id="username" name="username" placeholder="User name"/>
        </div>
    </div>
    <div class="form-group row">
        <label for="password" class="col-sm-2 col-form-label">Password:</label>
        <div class="col-sm-6">
        <input class="form-control" type="password" id="password" name="password" placeholder="Password"/>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <#if !isRegisterForm>
        <a href="/registration">Add new user</a>
    </#if>
    <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else >Sing In</#if></button>
</form>
</#macro>

<#macro logout>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="btn btn-primary" type="submit">Sign Out</button>
        </form>
</#macro>
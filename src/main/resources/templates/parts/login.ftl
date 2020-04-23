<#macro login>
    <div class="col"><h3>Log Into Site</h3></div>
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="col alert alert-danger" role="alert">
            Invalid login or password
        </div>
    </#if>

    <form action="/login" method="post">
        <div class="col-5 mt-3"><input class="form-control" type="text" name="username" placeholder="Login"/></div>
        <div class="col-5 mt-3"><input class="form-control" type="password" name="password" placeholder="Password"/></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="col mt-3"><button type="submit" class="btn btn-dark">Sign In</button></div>
<#--        <div class="col-5 mt-3"><label> User Name : <input type="text" name="username"/> </label></div>-->
<#--        <div><label> Password : <input type="password" name="password"/> </label></div>-->

<#--        <div><input type="submit" value="Sign In"/></div>-->

    </form>
</#macro>
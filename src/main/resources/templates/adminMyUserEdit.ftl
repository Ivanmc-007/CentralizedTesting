<#include "parts/security.ftl">
<#import "parts/common.ftl" as common>
<#import "parts/adminRoom.ftl" as adminRoom>
<@common.commonPage>
    <@adminRoom.adminPage>
        <h3 class="col mb-2">User editor</h3>
        <form action="/admin/users/${myUserEdit.id}" method="post">
            <div class="col">
            <input class="form-control ${(myUserLoginError??)?string('is-invalid','')} mb-3 mt-3" type="text" name = "myUserLogin" value="<#if myUserEdit??>${myUserEdit.login!}</#if>">
            </div>
            <#if myUserLoginError??>
                <div class="invalid-feedback">
                    ${myUserLoginError}
                </div>
            </#if>

            <#list roles as role>
                <div class="input-group col mt-2">
                    <label><input class="mr-2" type="checkbox" name="checkedRoles" value="${role}" ${myUserEdit.roles?seq_contains(role)?string("checked","")}>${role}</label>
                </div>
            </#list>

            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="col mt-2">
            <button type="submit" class="btn btn-dark">Save</button>
            </div>
        </form>


    </@adminRoom.adminPage>
</@common.commonPage>
<#include "parts/security.ftl">
<#import "parts/common.ftl" as common>
<#import "parts/adminRoom.ftl" as adminRoom>
<#import "parts/universitiesShow.ftl" as universitiesShow>
<@common.commonPage>
    <@adminRoom.adminPage>

        <#if removeError??>
            <div class="alert alert-danger" role="alert">${removeError}</div>
        </#if>

        <#if users?? && users?has_content>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Name</th>
                        <th scope="col">Roles</th>
                        <th scope="col">Is Active</th>
                        <th scope="col">Action1</th>
                        <th scope="col">Action2</th>
                        <th scope="col">Action3</th>
                    </tr>
                </thead>
                <tbody>
                    <#list users as currUser>
                        <tr>
                            <td>${currUser.id}</td>
                            <td>${currUser.login}</td>
                            <td><#list currUser.roles as currRole>
                                    ${currRole.name()}<#sep>, </#list>
                            </td>
                            <td>${(currUser.active)?string("Yes","No")}</td>
                            <td>
                                <a href="/admin/users/${currUser.id}">edit</a>
                            </td>
                            <td>
                                <a href="/admin/users/show/${currUser.id}">show</a>
                            </td>
                            <td>
                                <a href="/admin/users/removeAccept/${currUser.id}">remove</a>
                            </td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </#if>
    </@adminRoom.adminPage>
</@common.commonPage>
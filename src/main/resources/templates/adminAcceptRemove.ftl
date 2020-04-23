<#include "parts/security.ftl">
<#import "parts/common.ftl" as common>
<#import "parts/adminRoom.ftl" as adminRoom>
<@common.commonPage>
    <@adminRoom.adminPage>
        <#if removeMyUserId??>
            <form action="/admin/users/remove" method="post">
                <h3>Accept remove</h3>
                <div class="mt-3 mb-3">Are you sure? Do you want to remove user by id ${removeMyUserId}?</div>
                <input type="hidden" name="removeMyUserId" value="${removeMyUserId}" />
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit" class="btn btn-dark mr-3">Yes</button>
                <a class="btn btn-dark mr-3" href="/admin/users" role="button">No</a>
            </form>
        </#if>
    </@adminRoom.adminPage>
</@common.commonPage>
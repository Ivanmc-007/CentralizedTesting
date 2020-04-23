<#include "parts/security.ftl">
<#import "parts/common.ftl" as common>
<#import "parts/adminRoom.ftl" as adminRoom>
<@common.commonPage>
    <@adminRoom.adminPage>
        <form action="/admin/subjects/rename" method="post">
            <div class="col mt-3">Current name: ${subject.name!}</div>
            <div class="col-7 mt-3"><input class="form-control" type="text" name="newNameSubject" placeholder="New name"/></div>
            <input type="hidden" name="subjectId" value="${subject.id!}" />
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="col mt-3"><button type="submit" class="btn btn-dark">Rename</button></div>
        </form>
    </@adminRoom.adminPage>
</@common.commonPage>
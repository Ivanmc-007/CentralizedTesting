<#include "parts/security.ftl">
<#import "parts/common.ftl" as common>
<#import "parts/adminRoom.ftl" as adminRoom>
<@common.commonPage>
    <@adminRoom.adminPage>
        <div class="form-group mb-5">
        <div class="col mt-3">
            University id:
            <label class="font-weight-bold">${university.id}</label>, university name:
            <label class="font-weight-bold">${university.name}</label>
        </div>
        <div class="col form-group mt-3">List of subjects:</div>
        <#if subjects??>
            <form action="/admin/universities/setSubjects" method="post">
                <#list subjects as subject>
                    <div class="col mt-3">
                        <label><input type="checkbox" name="subjectId" value="${subject.id}" ${subjectsOfUniversity?seq_contains(subject)?string("checked","")}>${subject.name}</label>
                    </div>
                </#list>
                <div class="col mt-3"><button type="submit" class="btn btn-dark">Accept</button></div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="hidden" name="universityId" value="${university.id}" />
            </form>
        <#else>
        <#--            todo: Alert -->
            <div>List of universities is empty!</div>
        </#if>
        </div>
        <br>
    </@adminRoom.adminPage>
</@common.commonPage>
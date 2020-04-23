<#include "parts/security.ftl">
<#import "parts/common.ftl" as common>
<#import "parts/userRoom.ftl" as userRoom>
<@common.commonPage>
    <@userRoom.userPage>
        <form action="/myUser/subject/removeSubject" method="post">
            <h3>Accept remove</h3>
            <div class="mt-3 mb-3">Are you sure? Do you want to remove subject by name ${choice.subject.name!}?"</div>
            <input type="hidden" name="choiceId" value="${choice.id!}" />
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit" class="btn btn-dark mr-3">Yes</button>
            <a class="btn btn-dark mr-3" href="/myUser/subject" role="button">No</a>
        </form>
    </@userRoom.userPage>
</@common.commonPage>
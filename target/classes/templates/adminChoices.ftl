<#include "parts/security.ftl">
<#import "parts/common.ftl" as common>
<#import "parts/adminRoom.ftl" as adminRoom>
<@common.commonPage>
    <@adminRoom.adminPage>
        <h3>User choice</h3>
        <form class="col form-group row" action="/admin/choices" method="get">
            <input class="col-4 form-control mb-2 mr-sm-2 mb-sm-0" type="date" name="calendarStart" value="${calendarStart!}">
            <input class="col-4 form-control mb-2 mr-sm-2 mb-sm-0" type="date" name="calendarEnd" value="${calendarEnd!}">
            <input type="submit" class="btn btn-dark mb-2 mr-sm-2 mb-sm-0" value="Search">
        </form>
        <#if choices?? && choices?has_content>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">id</th>
                        <th scope="col">Date creation</th>
                        <th scope="col">Subject</th>
                        <th scope="col">University</th>
                        <th scope="col">User</th>
                        <th scope="col">Accepted</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <#list choices as choice>
                    <tr>
                        <td>${choice.id!}</td>
                        <td>${choice.dateCreation!}</td>
                        <td>${choice.subject.getName()!}</td>
                        <td>${choice.university.getName()!}</td>
                        <td>${choice.myUser.login!}</td>
                        <td <#if choice.accept>class="table-success"</#if>>${(choice.accept)?string("Accepted", "Didn't accept")}</td>
                        <form action="/admin/choices/${choice.id}" method="get">
                            <td>
                                <button type="submit" class="btn btn-dark">Accept</button>
                            </td>
                        </form>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </#if>
    </@adminRoom.adminPage>
</@common.commonPage>
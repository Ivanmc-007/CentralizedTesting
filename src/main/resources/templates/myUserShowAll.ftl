<#include "parts/security.ftl">
<#import "parts/common.ftl" as common>
<#import "parts/adminRoom.ftl" as adminRoom>
<@common.commonPage>
    <@adminRoom.adminPage>

        <#if currMyUser??>

            <table class="table col">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Login</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${currMyUser.id!}</td>
                        <td>${currMyUser.login!}</td>
                    </tr>
                </tbody>
            </table>

            <#if currMyUser.choices?? && currMyUser.choices?has_content>
            <br>
            <table class="table col">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">Subject</th>
                        <th scope="col">University</th>
                        <th scope="col">Date added</th>
                    </tr>
                </thead>
                <tbody>
                    <#list currMyUser.choices as choice>
                    <tr>
                        <td>${choice.subject.name!}</td>
                        <td>${choice.university.name!}</td>
                        <th scope="col">${choice.dateCreation!}</th>
                    </tr>
                    </#list>
                </tbody>
            </table>
            </#if>

            <br>
            <#if currMyUser.passport??>
                <table class="table col mb-5">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">Passport RB</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>

                    <tr>
                        <td>Series : </td>
                        <td colspan="3">${currMyUser.passport.series!}</td>
                    </tr>
                    <tr>
                        <td>Number : </td>
                        <td colspan="3">${currMyUser.passport.number!}</td>
                    </tr>
                    <tr>
                        <td>Name : </td>
                        <td colspan="3">${currMyUser.passport.firstName!}</td>
                    </tr>
                    <tr>
                        <td>Surname : </td>
                        <td colspan="3">${currMyUser.passport.lastName!}</td>
                    </tr>
                    </tbody>
                </table>
            <#else>
                <div class="alert alert-info" role="alert">
                    User does not have a passport!
                </div>
            </#if>
    </#if>

    </@adminRoom.adminPage>
</@common.commonPage>
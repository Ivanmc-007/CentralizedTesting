<#include "security.ftl">
<#macro userPage>
    <div class="row">
        <div class="col-3">
            <nav class="nav flex-column">
                <ul class="list-group list-group-flush">
                    <a class="list-group-item list-group-item-action" href="/myUser">Hello from the Ministry</a>
                    <a class="list-group-item list-group-item-action" href="/myUser/identityDocument">Identity document</a>
                    <a class="list-group-item list-group-item-action" href="/myUser/subject">Subjects</a>
                    <a class="list-group-item list-group-item-action" href="#">Permissions</a>
                </ul>

                <br/>
                <a class="nav-link" href="#">Helper *</a>

            </nav>
        </div>

        <div class="col">
            <#nested>
        </div>

    </div>

</#macro>
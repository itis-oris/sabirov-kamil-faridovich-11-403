<#macro layout>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quest Platform</title>
        <link rel="stylesheet" href="/quest_platform/css/style.css">
    </head>
    <body>
    <#include "../fragments/navbar.ftlh">

    <div class="container">
        <#nested>
    </div>


    </body>
    </html>
</#macro>
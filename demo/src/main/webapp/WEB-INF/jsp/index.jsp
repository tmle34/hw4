<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <title>SpringBoot</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
            width: 150px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }


    </style>
</head>
<body>

<script type="text/javascript">
    var hide = false;

    function toggleTable()
    {
        if (hide == false)
        {

            hide = true;
            document.getElementById('table').style.visibility = 'visible';
        }
        else
        {
            hide = false;
            document.getElementById('table').style.visibility = 'hidden';
        }
    }
</script>


<form method="post" action="/save">
    <textarea rows="15" cols="30" name = "completeJoke">${joke}</textarea>
    <br><br>
    <input type="submit" value="Save Joke">
</form>

<button type="button" onclick="toggleTable()" style="width: 50px; height: 50px;" >Load Table</button>

<div id="table" style="visibility:hidden">
<h2>HTML Table</h2>
    <table >
        <tr>
            <th>ID</th>
            <th>The Joke</th>
        </tr>
        <c:forEach var = "listitem" items = "${listfor}">
            <tr>
                <td>${listitem.getId()}</td>
                <td>${listitem.getCompletejoke()}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>

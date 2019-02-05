
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Person List</title>
  </head>
  <body>
    <h1>Account List</h1>

    <br/><br/>
    <div>
      <table border="1">
        <tr>
          <th>Account ID</th>
          <th>Iban</th>
          <th>Type</th>
          <th>Interets</th>
          <th>Frais tenu compte</th>
        </tr>
		
        <tr th:each="account : ${accounts}">
          <td>th:text="${account.ID}"</td>
          <td>th:text="${account.iban}"</td>
          <td>th:text="${account.type}"</td>
          <td>th:text="${account.interet}"</td>
          <td>th:text="${account.frais_tenu_compte}"</td>
        </tr>
      </table>
    </div>
  </body>

</html>
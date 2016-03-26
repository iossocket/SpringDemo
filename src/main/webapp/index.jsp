<html>
<body>
<h2>Hello World!</h2>
<a href="/hello">hello</a>

<form action="/springmvc/testMethod" method="post">
    <button type="submit">Test Post Method</button>
</form>

<br/>
<a href="/springmvc/testParamsAndHeader?username=zxl&age=11">testParamsAndHeader</a>

<br/>
<a href="/springmvc/testAntPath/abac/abc">Test Ant Request</a>

<br/>
<a href="/springmvc/testPathVariable/12">testPathVariable</a>
</body>

<br/>
<form action="/springmvc/testPojo" method="post">
    username: <input type="text" name="username"/><br/>
    password: <input type="password" name="password"/><br/>
    email: <input type="text" name="email"/><br/>
    age: <input type="text" name="age"/><br/>
    city: <input type="text" name="address.city"/><br/>
    province: <input type="text" name="address.province"/><br/>
    <input type="submit" value="Submit">
</form>

</html>

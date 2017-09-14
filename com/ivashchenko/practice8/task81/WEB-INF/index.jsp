<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <form action="/compute" method="post">

        <b>Select functionName:</b><br/>
        <select name="functionName" id="functionName">
            <option value="sin">Sin</option>
            <option value="cos">Cos</option>
            <option value="tan">Tan</option>
            <option value="asin">Asin</option>
            <option value="acos">Acos</option>
            <option value="atan">Atan</option>
        </select>
        <br/>

        <b>Value to compute:</b><br/>
        <input type="text" name="valueToCompute" datatype="number">
        <br/>

        <b>Select units to represent calculations: </b><br>
        <select name="units" id="representationSelect">
            <option value="degrees">Degrees</option>
            <option value="radians">Radians</option>
        </select>
        <br/>

        <b>Enter how many numbers after comma:</b><br/>
        <input type="text" name="digitsAfterComma">
        <br/>

        <input type="submit" value="Calculate">
    </form>
</body>
</html>
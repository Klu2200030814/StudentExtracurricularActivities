<!DOCTYPE html>
<html>
<head>
    <title>Upload CSV</title>
    <link rel="stylesheet" type="text/css" href="static/css/style.css">
</head>
<body>
    <h2>Upload CSV File for Customer Registration</h2>
    <form action="/upload" method="post" enctype="multipart/form-data">
        <label for="file">Select CSV File:</label>
        <input type="file" id="file" name="file">
        <input type="submit" value="Upload">
    </form>
</body>
</html>

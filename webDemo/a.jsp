<%@ page contentType="text/html; charset=GBK" language="java" errorPage=""%>  
<!DOCTYPE html>
<html>
<head>
	<title>��ӭ</title>
</head>
<body>
	<%!
	    public int count;
	    public String info(){
	        return "hello";
	    }
	%>
	���ʴ�����
	<%
		out.println(count++);
		out.println(info());
	%>
</body>
</html>
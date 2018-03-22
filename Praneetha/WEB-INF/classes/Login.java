import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class Login extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			HttpSession session=request.getSession(true);
			PrintWriter out=response.getWriter();
			Connection con=(Connection)session.getAttribute("con");
			Statement stmt=(Statement)session.getAttribute("stmt");
			ResultSet rs;
			if(con==null)
			{
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
				}
				catch(ClassNotFoundException cls)
				{
				}
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				String url="jdbc:mysql://localhost:3306/leavemgmt?user=root&password=root";
				con=DriverManager.getConnection(url);
				stmt=con.createStatement();
				session.setAttribute("con", con);
				session.setAttribute("stmt", stmt);
			}
			
			if(request.getParameter("Register")!=null)
			{
				out.println("<html>");
				out.println("<head>");
				out.println("<script type=\"text/javascript\">");
				out.println("function resetForm()");
				out.println("{");
				out.println("document.getElementById(\"myform\").reset();");
				out.println("}");
				out.println("");
				out.println("function CountryChange()");
				out.println("{");
				out.println("var select = document.getElementsByName(\"txtState\")[0];");
				out.println("var sel=document.getElementsByName(\"txtCountry\")[0];");
				out.println("if (sel.options[sel.selectedIndex].text == \"India\")");
				out.println("{");
				out.println("var options = [\"Select State\", \"Andhra Pradesh\", \"Arunachal Pradesh\", \"Assam\", \"Bihar\", \"Chhattisgarh\", \"Goa\", \"Gujarat\", \"Haryana\", \"Himachal Pradesh\", \"Jammu and Kashmir\", \"Jharkhand\", \"Karnataka\", \"Kerala\", \"Madhya Pradesh\", \"Maharashtra\", \"Manipur\", \"Meghalaya\", \"Mizoram\", \"Nagaland\", \"Odisha\", \"Punjab\", \"Rajasthan\", \"Sikkim\", \"Tamil Nadu\", \"Telangana\", \"Tripura\", \"Uttar Pradesh\", \"Uttarakhand\", \"West Bengal\"]; ");
				out.println("// Optional: Clear all existing options first:");
				out.println("select.innerHTML = \"\";");
				out.println("// Populate list with options:");
				out.println("for(var i = 0; i < options.length; i++)");
				out.println("{");
				out.println("var opt = options[i];");
				out.println("select.innerHTML += \"<option value=\'\" + opt + \"\'>\" + opt + \"</option>\";");
				out.println("}");
				out.println("}");
				out.println("else");
				out.println("{");
				out.println("var options = [\"Select State\", \"Alabama\", \"Alaska\", \"Arizona\", \"Arkansas\", \"California\", \"Colorado\", \"Connecticut\", \"Delaware\", \"Florida\", \"Georgia\", \"Hawaii\", \"Idaho\", \"Illinois\", \"Indiana\", \"Iowa\", \"Kansas\", \"Kentucky\", \"Louisiana\", \"Maine\", \"Maryland\", \"Massachusetts\", \"Michigan\", \"Minnesota\", \"Mississippi\", \"Missouri\", \"Montana\", \"Nebraska\", \"Nevada\", \"New Hampshire\", \"New Jersey\", \"New Mexico\", \"New York\", \"North Carolina\", \"North Dakota\", \"Ohio\", \"Oklahoma\", \"Oregon\", \"Pennsylvania\", \"Rhode Island\", \"South Carolina\", \"South Dakota\", \"Tennessee\", \"Texas\", \"Utah\", \"Vermont\", \"Virginia\", \"Washington\", \"West Virginia\", \"Wisconsin\", \"Wyoming\"]; ");
				out.println("// Optional: Clear all existing options first:");
				out.println("select.innerHTML = \"\";");
				out.println("// Populate list with options:");
				out.println("for(var i = 0; i < options.length; i++)");
				out.println("{");
				out.println("var opt = options[i];");
				out.println("select.innerHTML += \"<option value=\'\" + opt + \"\'>\" + opt + \"</option>\";");
				out.println("}");
				out.println("}");
				out.println("}");
				out.println("");
				out.println("function isValidate()");
				out.println("{");
				out.println("if(document.getElementsByName(\"txtUser\")[0].value.length == 0)");
				out.println("{");
				out.println("alert(\"Please Specify the UserId\");");
				out.println("return false;");
				out.println("}");
				out.println("if (document.getElementsByName('txtUser')[0].value.length<5 ||  document.getElementsByName('txtUser')[0].value.length >12)");
				out.println("{");
				out.println("alert(\"UserId must be 5 to 12 characters long.\");");
				out.println("return false;");
				out.println("}");
				out.println("if (document.getElementsByName('txtPass')[0].value.length == 0)");
				out.println("{");
				out.println("alert(\"You have not entered Password.\");");
				out.println("return false;");
				out.println("}");
				out.println("if (document.getElementsByName('txtPass')[0].value.length<7 ||  document.getElementsByName('txtPass')[0].value.length >12)");
				out.println("{");
				out.println("alert(\"Password must be 7 to 12 characters long.\");");
				out.println("return false;");
				out.println("}");
				out.println("if (document.getElementsByName('txtConfPass')[0].value.length == 0)");
				out.println("{");
				out.println("alert(\"Please confirm your entered Password.\");");
				out.println("return false;");
				out.println("}");
				out.println("if (document.getElementsByName('txtPass')[0].value!=document.getElementsByName('txtConfPass')[0].value)");
				out.println("{");
				out.println("alert(\"Password and Confirm Password does not match.\");");
				out.println("return false;");
				out.println("}");
				out.println("if(document.getElementsByName('txtName')[0].value.length==0)");
				out.println("{");
				out.println("alert(\"Name cannot be left blank.\");");
				out.println("}");
				out.println("var letters = /^[A-Za-z .]+$/;  ");
				out.println("if(! document.getElementsByName('txtName')[0].value.match(letters))  ");
				out.println("{  ");
				out.println("alert(\"Name cannot contain anything other than Alphabets.\");  ");
				out.println("return false;");
				out.println("}");
				out.println("var sel=document.getElementsByName(\"txtCountry\")[0];");
				out.println("if (sel.selectedIndex == 0)");
				out.println("{");
				out.println("alert(\"Please select appropriate Country.\");");
				out.println("return false;");
				out.println("}");
				out.println("var sel=document.getElementsByName(\"txtState\")[0];");
				out.println("if (sel.selectedIndex == 0)");
				out.println("{");
				out.println("alert(\"Please select appropriate State.\");");
				out.println("return false;");
				out.println("}");
				out.println("if (document.getElementsByName('txtZip')[0].value.length == 0)");
				out.println("{");
				out.println("alert(\"You have not entered Zip Code.\");");
				out.println("return false;");
				out.println("}");
				out.println("if (isNaN(document.getElementsByName('txtZip')[0].value))");
				out.println("{");
				out.println("alert(\"Zip Code must be Numeric.\");");
				out.println("return false;");
				out.println("}");
				out.println("var x = document.forms[\"register\"][\"txtEmail\"].value;");
				out.println("var atpos = x.indexOf(\"@\");");
				out.println("var dotpos = x.lastIndexOf(\".\");");
				out.println("if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {");
				out.println("alert(\"Not a valid e-mail address\");");
				out.println("return false;");
				out.println("}");
				out.println("if((! document.getElementsByName('gender')[0].checked) && (! document.getElementsByName('gender')[1].checked))");
				out.println("{");
				out.println("alert(\"Please select Gender\");");
				out.println("return false;");
				out.println("}");
				out.println("if((! document.getElementsByName('language')[0].checked) && (! document.getElementsByName('language')[1].checked))");
				out.println("{");
				out.println("alert(\"Please select Language.\");");
				out.println("return false;");
				out.println("}");
				out.println("document.form.submit();");
				out.println("return(true);");
				out.println("}");
				out.println("</script>");
				out.println("<style type=\"text/css\">");
				out.println(".left_panel");
				out.println("{");
				out.println("text-align:right;");
				out.println("}");
				out.println("</style>");
				out.println("</head>");
				out.println("<body>");
				out.println("<center>");
				out.println("<h1>Registration Form</h1>");
				out.println("<form id=\"myform\" name=\"register\" method=\"post\" action=\"Login\">");
				out.println("<table style=\"border-spacing: 30px 0;\">");
				out.println("<tr><td class=\"left_panel\">User id:</td><td><input type=\"text\" name=\"txtUser\" /></td></tr>");
				out.println("<tr><td class=\"left_panel\">Password:</td><td><input type=\"password\" name=\"txtPass\"/></td></tr>");
				out.println("<tr><td class=\"left_panel\">Confirm<br/>Password:</td><td><input type=\"password\" name=\"txtConfPass\"/></td></tr>");
				out.println("<tr><td class=\"left_panel\">Name:</td><td><input type=\"text\" name=\"txtName\" /></td></tr>");
				out.println("<tr><td class=\"left_panel\">Address:</td><td><input type=\"text\" name=\"txtAddress\" /></td></tr>");
				out.println("<tr><td class=\"left_panel\">Country:</td>");
				out.println("<td>");
				out.println("<select name=\"txtCountry\" onchange=\"CountryChange()\">");
				out.println("<option value=\"Select Country\">Select Country</option>");
				out.println("<option value=\"India\">India</option>");
				out.println("<option value=\"USA\">USA</option>");
				out.println("</select>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr><td class=\"left_panel\">State</td>");
				out.println("<td>");
				out.println("<select name=\"txtState\">");
				out.println("<option value=\"Select State\">Select State</option>");
				out.println("</select>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr><td class=\"left_panel\">Zip Code:</td><td><input type=\"text\" name=\"txtZip\" /></td></tr>");
				out.println("<tr><td class=\"left_panel\">Email:</td><td><input type=\"text\" name=\"txtEmail\" /></td></tr>");
				out.println("<tr><td class=\"left_panel\">Sex:</td>");
				out.println("<td><input type=\"radio\" name=\"gender\" value=\"M\">Male");
				out.println("<input type=\"radio\" name=\"gender\" value=\"F\">Female</td></tr>");
				out.println("<tr><td class=\"left_panel\">Language:</td>");
				out.println("<td>");
				out.println("<input type=\"checkbox\" name=\"language\" value=\"E\">English");
				out.println("<input type=\"checkbox\" name=\"language\" value=\"N\">Non English");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr><td class=\"left_panel\">About:</td><td><textArea name=\"txtAbout\" Rows=\"10\" Cols=\"22\">Describe Yourself</textArea><br/><br/></td></tr>");
				out.println("<tr>");
				out.println("<th colspan='2'><input type=\"submit\" name=\"doRegister\" value=\"Register User\" onClick=\"return isValidate();\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				out.println("<input type=\"button\" onclick=\"resetForm()\" value=\"Reset\"></th>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</center>");
				out.println("</body>");
				out.println("</html>");
			}
			
			if(request.getParameter("doRegister")!=null)
			{
				String user=request.getParameter("txtUser");
				String pass=request.getParameter("txtPass");
				String fullname=request.getParameter("txtName");
				String address;
				if(request.getParameter("txtAddress")==null)
					address="";
				else
					address=request.getParameter("txtAddress");
				String country=request.getParameter("txtCountry");
				String State=request.getParameter("txtState");
				int zip=Integer.parseInt(request.getParameter("txtZip"));
				String email=request.getParameter("txtEmail");
				String gender=request.getParameter("gender");
				String lang=request.getParameter("language");
				String about;
				if(request.getParameter("txtAbout")==null)
					about="";
				else
					about=request.getParameter("txtAbout");
				out.println("<html>");
				out.println("<head>");
				out.println("<script type=\"text/javascript\">");
				out.println("function resetForm()");
				out.println("{");
				out.println("document.getElementById(\"myform\").reset();");
				out.println("}");
				out.println("");
				out.println("function CountryChange()");
				out.println("{");
				out.println("var select = document.getElementsByName(\"txtState\")[0];");
				out.println("var sel=document.getElementsByName(\"txtCountry\")[0];");
				out.println("if (sel.options[sel.selectedIndex].text == \"India\")");
				out.println("{");
				out.println("var options = [\"Select State\", \"Andhra Pradesh\", \"Arunachal Pradesh\", \"Assam\", \"Bihar\", \"Chhattisgarh\", \"Goa\", \"Gujarat\", \"Haryana\", \"Himachal Pradesh\", \"Jammu and Kashmir\", \"Jharkhand\", \"Karnataka\", \"Kerala\", \"Madhya Pradesh\", \"Maharashtra\", \"Manipur\", \"Meghalaya\", \"Mizoram\", \"Nagaland\", \"Odisha\", \"Punjab\", \"Rajasthan\", \"Sikkim\", \"Tamil Nadu\", \"Telangana\", \"Tripura\", \"Uttar Pradesh\", \"Uttarakhand\", \"West Bengal\"]; ");
				out.println("// Optional: Clear all existing options first:");
				out.println("select.innerHTML = \"\";");
				out.println("// Populate list with options:");
				out.println("for(var i = 0; i < options.length; i++)");
				out.println("{");
				out.println("var opt = options[i];");
				out.println("select.innerHTML += \"<option value=\'\" + opt + \"\'>\" + opt + \"</option>\";");
				out.println("}");
				out.println("}");
				out.println("else");
				out.println("{");
				out.println("var options = [\"Select State\", \"Alabama\", \"Alaska\", \"Arizona\", \"Arkansas\", \"California\", \"Colorado\", \"Connecticut\", \"Delaware\", \"Florida\", \"Georgia\", \"Hawaii\", \"Idaho\", \"Illinois\", \"Indiana\", \"Iowa\", \"Kansas\", \"Kentucky\", \"Louisiana\", \"Maine\", \"Maryland\", \"Massachusetts\", \"Michigan\", \"Minnesota\", \"Mississippi\", \"Missouri\", \"Montana\", \"Nebraska\", \"Nevada\", \"New Hampshire\", \"New Jersey\", \"New Mexico\", \"New York\", \"North Carolina\", \"North Dakota\", \"Ohio\", \"Oklahoma\", \"Oregon\", \"Pennsylvania\", \"Rhode Island\", \"South Carolina\", \"South Dakota\", \"Tennessee\", \"Texas\", \"Utah\", \"Vermont\", \"Virginia\", \"Washington\", \"West Virginia\", \"Wisconsin\", \"Wyoming\"]; ");
				out.println("// Optional: Clear all existing options first:");
				out.println("select.innerHTML = \"\";");
				out.println("// Populate list with options:");
				out.println("for(var i = 0; i < options.length; i++)");
				out.println("{");
				out.println("var opt = options[i];");
				out.println("select.innerHTML += \"<option value=\'\" + opt + \"\'>\" + opt + \"</option>\";");
				out.println("}");
				out.println("}");
				out.println("}");
				out.println("");
				out.println("function isValidate()");
				out.println("{");
				out.println("if(document.getElementsByName(\"txtUser\")[0].value.length == 0)");
				out.println("{");
				out.println("alert(\"Please Specify the UserId\");");
				out.println("return false;");
				out.println("}");
				out.println("if (document.getElementsByName('txtUser')[0].value.length<5 ||  document.getElementsByName('txtUser')[0].value.length >12)");
				out.println("{");
				out.println("alert(\"UserId must be 5 to 12 characters long.\");");
				out.println("return false;");
				out.println("}");
				out.println("if (document.getElementsByName('txtPass')[0].value.length == 0)");
				out.println("{");
				out.println("alert(\"You have not entered Password.\");");
				out.println("return false;");
				out.println("}");
				out.println("if (document.getElementsByName('txtPass')[0].value.length<7 ||  document.getElementsByName('txtPass')[0].value.length >12)");
				out.println("{");
				out.println("alert(\"Password must be 7 to 12 characters long.\");");
				out.println("return false;");
				out.println("}");
				out.println("if (document.getElementsByName('txtConfPass')[0].value.length == 0)");
				out.println("{");
				out.println("alert(\"Please confirm your entered Password.\");");
				out.println("return false;");
				out.println("}");
				out.println("if (document.getElementsByName('txtPass')[0].value!=document.getElementsByName('txtConfPass')[0].value)");
				out.println("{");
				out.println("alert(\"Password and Confirm Password does not match.\");");
				out.println("return false;");
				out.println("}");
				out.println("if(document.getElementsByName('txtName')[0].value.length==0)");
				out.println("{");
				out.println("alert(\"Name cannot be left blank.\");");
				out.println("}");
				out.println("var letters = /^[A-Za-z .]+$/;  ");
				out.println("if(! document.getElementsByName('txtName')[0].value.match(letters))  ");
				out.println("{  ");
				out.println("alert(\"Name cannot contain anything other than Alphabets.\");  ");
				out.println("return false;");
				out.println("}");
				out.println("var sel=document.getElementsByName(\"txtCountry\")[0];");
				out.println("if (sel.selectedIndex == 0)");
				out.println("{");
				out.println("alert(\"Please select appropriate Country.\");");
				out.println("return false;");
				out.println("}");
				out.println("var sel=document.getElementsByName(\"txtState\")[0];");
				out.println("if (sel.selectedIndex == 0)");
				out.println("{");
				out.println("alert(\"Please select appropriate State.\");");
				out.println("return false;");
				out.println("}");
				out.println("if (document.getElementsByName('txtZip')[0].value.length == 0)");
				out.println("{");
				out.println("alert(\"You have not entered Zip Code.\");");
				out.println("return false;");
				out.println("}");
				out.println("if (isNaN(document.getElementsByName('txtZip')[0].value))");
				out.println("{");
				out.println("alert(\"Zip Code must be Numeric.\");");
				out.println("return false;");
				out.println("}");
				out.println("var x = document.forms[\"register\"][\"txtEmail\"].value;");
				out.println("var atpos = x.indexOf(\"@\");");
				out.println("var dotpos = x.lastIndexOf(\".\");");
				out.println("if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {");
				out.println("alert(\"Not a valid e-mail address\");");
				out.println("return false;");
				out.println("}");
				out.println("if((! document.getElementsByName('gender')[0].checked) && (! document.getElementsByName('gender')[1].checked))");
				out.println("{");
				out.println("alert(\"Please select Gender\");");
				out.println("return false;");
				out.println("}");
				out.println("if((! document.getElementsByName('language')[0].checked) && (! document.getElementsByName('language')[1].checked))");
				out.println("{");
				out.println("alert(\"Please select Language.\");");
				out.println("return false;");
				out.println("}");
				out.println("document.form.submit();");
				out.println("return(true);");
				out.println("}");
				out.println("</script>");
				out.println("<style type=\"text/css\">");
				out.println(".left_panel");
				out.println("{");
				out.println("text-align:right;");
				out.println("}");
				out.println("</style>");
				out.println("</head>");
				out.println("<body>");
				out.println("<center>");
				out.println("<h1>Registration Form</h1>");
				rs=stmt.executeQuery("select * from user where UserId like '"+user+"'");
				if(rs.next())
				{
					out.println("<center><h3 style=\"color:red;\">UserId already Registered.</h3></center>");
				}
				else
				{
					stmt.execute("insert into user (UserId, Password, UserName, Address, Country, State, ZipCode, Email, Sex, Language, About) values('"+user+"','"+pass+"','"+fullname+"','"+address+"','"+country+"','"+State+"',"+zip+",'"+email+"','"+gender+"','"+lang+"','"+about+"')");
					rs=stmt.executeQuery("Select empId from user where UserId='"+user+"'");
					if(rs.next())
						out.println("<center><h3 style=\"color:green;\">Successfully Registered. Your Employee Id : " + rs.getInt(1) + "</h3></center>");
				}
				out.println("<form id=\"myform\" name=\"register\" method=\"post\" action=\"Login\">");
				out.println("<table style=\"border-spacing: 30px 0;\">");
				out.println("<tr><td class=\"left_panel\">User id:</td><td><input type=\"text\" name=\"txtUser\" /></td></tr>");
				out.println("<tr><td class=\"left_panel\">Password:</td><td><input type=\"password\" name=\"txtPass\"/></td></tr>");
				out.println("<tr><td class=\"left_panel\">Confirm<br/>Password:</td><td><input type=\"password\" name=\"txtConfPass\"/></td></tr>");
				out.println("<tr><td class=\"left_panel\">Name:</td><td><input type=\"text\" name=\"txtName\" /></td></tr>");
				out.println("<tr><td class=\"left_panel\">Address:</td><td><input type=\"text\" name=\"txtAddress\" /></td></tr>");
				out.println("<tr><td class=\"left_panel\">Country:</td>");
				out.println("<td>");
				out.println("<select name=\"txtCountry\" onchange=\"CountryChange()\">");
				out.println("<option value=\"Select Country\">Select Country</option>");
				out.println("<option value=\"India\">India</option>");
				out.println("<option value=\"USA\">USA</option>");
				out.println("</select>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr><td class=\"left_panel\">State</td>");
				out.println("<td>");
				out.println("<select name=\"txtState\">");
				out.println("<option value=\"Select State\">Select State</option>");
				out.println("</select>");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr><td class=\"left_panel\">Zip Code:</td><td><input type=\"text\" name=\"txtZip\" /></td></tr>");
				out.println("<tr><td class=\"left_panel\">Email:</td><td><input type=\"text\" name=\"txtEmail\" /></td></tr>");
				out.println("<tr><td class=\"left_panel\">Sex:</td>");
				out.println("<td><input type=\"radio\" name=\"gender\" value=\"M\">Male");
				out.println("<input type=\"radio\" name=\"gender\" value=\"F\">Female</td></tr>");
				out.println("<tr><td class=\"left_panel\">Language:</td>");
				out.println("<td>");
				out.println("<input type=\"checkbox\" name=\"language\" value=\"E\">English");
				out.println("<input type=\"checkbox\" name=\"language\" value=\"N\">Non English");
				out.println("</td>");
				out.println("</tr>");
				out.println("<tr><td class=\"left_panel\">About:</td><td><textArea name=\"txtAbout\" Rows=\"10\" Cols=\"22\">Describe Yourself</textArea><br/><br/></td></tr>");
				out.println("<tr>");
				out.println("<th colspan='2'><input type=\"submit\" name=\"doRegister\" value=\"Register User\" onClick=\"return isValidate();\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				out.println("<input type=\"button\" onclick=\"resetForm()\" value=\"Reset\"></th>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</center>");
				out.println("</body>");
				out.println("</html>");
			}
			if(request.getParameter("Log")!=null)
			{
				session.invalidate();
				response.sendRedirect("index.htm");
			}
			if(request.getParameter("validate")!=null)
			{
				String empId=request.getParameter("txtEmpId");
				String pass=request.getParameter("txtPass");
				rs=stmt.executeQuery("select * from user where EmpId=" + empId);
				if(rs.next())
				{
					if(pass.equals(rs.getString("Password")))
					{
						String user=rs.getString("UserId");
						session.setAttribute("user", user);
						session.setAttribute("pass", pass);
						RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/LeaveMgmt");
						rd.forward(request, response);
					}
					else
					{
						out.println("<html>");
						out.println("<head>");
						out.println("</head>");
						out.println("<body>");
						out.println("<center>");
						out.println("<form method=\"post\" action=\"Login\">");
						out.println("<center><h3 style=\"color:red;\">Invalid Employee Id / Password.</h3></center>");
						out.println("<table>");
						out.println("<tr>");
						out.println("<td>Employee Id : </td>");
						out.println("<td><input type=\"text\" name=\"txtEmpId\" /></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td>Password  : </td>");
						out.println("<td><input type=\"password\" name=\"txtPass\"/></td><br/><br/><br/>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<th colspan=\"2\"><input type=\"submit\" name=\"validate\" value=\"Sign In\">");
						out.println("<input type=\"submit\" name=\"Register\" value=\"New Registration\"></th>");
						out.println("</tr>");
						out.println("</table>");
						out.println("</form>");
						out.println("</center>");
						out.println("</body>");
						out.println("</html>");
					}
				}
				else
				{
						out.println("<html>");
						out.println("<head>");
						out.println("</head>");
						out.println("<body>");
						out.println("<center>");
						out.println("<form method=\"post\" action=\"Login\">");
						out.println("<center><h3 style=\"color:red;\">Invalid Employee Id / Password.</h3></center>");
						out.println("<table>");
						out.println("<tr>");
						out.println("<td>Employee Id : </td>");
						out.println("<td><input type=\"text\" name=\"txtEmpId\" /></td>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<td>Password  : </td>");
						out.println("<td><input type=\"password\" name=\"txtPass\"/></td><br/><br/><br/>");
						out.println("</tr>");
						out.println("<tr>");
						out.println("<th colspan=\"2\"><input type=\"submit\" name=\"validate\" value=\"Sign In\">");
						out.println("<input type=\"submit\" name=\"Register\" value=\"New Registration\"></th>");
						out.println("</tr>");
						out.println("</table>");
						out.println("</form>");
						out.println("</center>");
						out.println("</body>");
						out.println("</html>");
				}
			}
			out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class LeaveMgmt extends HttpServlet
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
			String user=(String)session.getAttribute("user");
			if (session.isNew() && user==null)
			{
				session.invalidate();
				response.sendRedirect("index.htm");
				return;
			}
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
			out.println("<html>");
			out.println("<head>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div style=\"width:900px;height:100%;margin:0 auto;\">");
			out.println("<div style=\"width:200px;height:50px;float:right;\">");
			out.println("<h4>Welcome " + user + "&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"Login?Log=SignOut\">Sign Out</a></h4><br/><br/>");
			out.println("</div>");
			out.println("<div style=\"width:100%;position:relative;top:50px;float:left;\">");
			out.println("<form method=\"get\" action=\"LeaveMgmt\">");
			if(!user.equals("admin"))
			{
				if(request.getParameter("Apply")!=null)
				{
					rs=stmt.executeQuery("select * from leaveapp where UserId='"+user+"' and Date_Leave like '" + request.getParameter("txtDate") +"'");
					if(rs.next())
					{
						out.println("<center><h3 style=\"color:red;\">You have already applied leave for this date.</h3></center>");
					}
					else
					{
						stmt.execute("insert into leaveapp(Date_Leave, Reason, Status, UserId) values('" + request.getParameter("txtDate") + "','" + request.getParameter("txtReason").toString() + "',0,'" + user + "')");
					}
				}
				out.println("<table align=\"center\">");
				out.println("<tr>");
				out.println("<th colspan='2'><h2>Leave Application</h2></th>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>Leave Date</td>");
				out.println("<td><input type=\"Date\" name=\"txtDate\" /> </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>Reason for Leave</td>");
				out.println("<td><input type=\"text\" name=\"txtReason\" /></td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<th colspan=\"2\"><input type=\"submit\" name=\"Apply\" value=\"Apply Leave\"></th>");
				out.println("</tr>");
				out.println("</table>");
			}
			else
			{
				if(request.getParameter("Approve")!=null)
				{
					if(request.getParameter("Id")!=null)
					{
						stmt.execute("update leaveapp set status=1 where Id=" + Integer.parseInt(request.getParameter("Id").toString()));
					}
					else
					{
						out.println("<center><h3 style=\"color:red;\">Please select before taking any action.</h3></center>");
					}
				}
				if(request.getParameter("Reject")!=null)
				{
					if(request.getParameter("Id")!=null)
					{
						stmt.execute("update leaveapp set status=2 where Id=" + Integer.parseInt(request.getParameter("Id").toString()));
					}
					else
					{
						out.println("<center><h3 style=\"color:red;\">Please select before taking any action.</h3></center>");
					}
				}
				out.println("<table align=\"center\" style=\"border-spacing: 30px 0;\">");
				out.println("<tr>");
				out.println("<th colspan='4'><h2>Applied Leaves</h2></th>");
				out.println("</tr>");
				out.println("<tr><th></th><th>Leave Date</th><th>Reason for Leave</th><th>Applied by</th><th>Approval Status</th></tr>");
				rs=stmt.executeQuery("select Id, Date_Leave, Reason, UserName, Status from user, leaveapp where user.UserId=leaveapp.UserId order by Status");
				while(rs.next())
				{
					String status_leave, select_leave="";
					if(rs.getInt("Status")==0)
					{
						select_leave="<input type=\"radio\" name=\"Id\" value=\""+ rs.getInt("Id")+"\" />";
						status_leave="<input type=\"submit\" name=\"Approve\" value=\"Approve Leave\">&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"submit\" name=\"Reject\" value=\"Reject Leave\">";
					}
					else if(rs.getInt("Status")==1)
						status_leave="Leave Approved";
					else
						status_leave="Leave Rejected";
					out.println("<tr><td>"+select_leave+"</td><td>"+rs.getString("Date_Leave")+"</td><td>"+rs.getString("Reason")+"</td><td>"+rs.getString("UserName")+"</td><td>"+status_leave+"</td></tr>");
				}
				out.println("</table>");
			}
			out.println("</form>");
			if(!user.equals("admin"))
			{
				out.println("<br/><br/><br/>");
				out.println("<table align=\"center\" style=\"border-spacing: 30px 0;\">");
				out.println("<tr>");
				out.println("<th colspan='3'><h2>Applied Leaves</h2></th>");
				out.println("</tr>");
				out.println("<tr><th>Leave Date</th><th>Reason for Leave</th><th>Approval Status</th></tr>");
				rs=stmt.executeQuery("select * from leaveapp where UserId like '" + user +"' order by Date_Leave desc");
				while(rs.next())
				{
					String status_leave;
					if(rs.getInt(3)==0)
						status_leave="Waiting for Approval";
					else if(rs.getInt(3)==1)
						status_leave="Leave Approved";
					else
						status_leave="Leave Rejected";
					out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+status_leave+"</td></tr>");
				}
				out.println("</table>");
			}
			else
			out.println("</div>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}
}
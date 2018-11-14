package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class DisplayTrains extends GenericServlet
{
	public Connection con;
	@Override
	public void init()
	{
		con=DBConnection.getCon();
	}
	public void service(ServletRequest req,ServletResponse res) throws IOException
	{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		String trainID=req.getParameter("trainNo");
		try
		{
			PreparedStatement ps=con.prepareStatement("select * from TrainInfo where trainid=?");
			ps.setString(1, trainID);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				pw.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+rs.getInt(5));
			}
		}
		catch(SQLException e)
		{
			pw.println("Invalid Train Number");
		}
	}

}

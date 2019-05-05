package main.java;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class myServlet extends HttpServlet {
    public myServlet(){}
    public void doGet(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
        this.performTask(var1, var2);
    }

    private void performTask(HttpServletRequest var1, HttpServletResponse var2) {
        try {
            PrintWriter var5 = var2.getWriter();
            var2.setContentType("text/html; charset=CP1251");
            var5.println("<HTML><HEAD>");
            var5.println("<TITLE>Answer</TITLE>");
            var5.println("</HEAD><BODY><BR>");
            var5.print("<h2>Answer: ");
            String nCount=var1.getParameter("n");
            String xValue=var1.getParameter("x");
            String stroka=var1.getParameter("mas");
            if(nCount.compareTo("")==0||xValue.compareTo("")==0||stroka.compareTo("")==0)
            {
                var5.println("Not enough dates! </h2>");
                var5.println("</BODY></HTML>");
                var5.close();
            }
            else
            {
                StringTokenizer str = new StringTokenizer(stroka);
                int n = Integer.parseInt(nCount);
                if (n + 1 != str.countTokens()) {
                    var5.println("Wrong count of arguments! </h2>");
                    var5.println("</BODY></HTML>");
                    var5.close();
                } else {
                    double mas[] = new double[n + 1];
                    for (int i = 0; i < n + 1; i++)
                        mas[i] = Double.parseDouble(str.nextToken());
                    double x = Double.parseDouble(xValue);
                    double result = calculating(mas, n + 1, x);
                    var5.println(result);
                    var5.println("</h2></BODY></HTML>");
                    var5.close();
                }
                var5.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double calculating(double[] mas, int nCount, double x) {
        double sum=0;
        for(int i=0;i<nCount;i++)
            sum+=Math.pow(x,i)*mas[nCount-1-i];
        return sum;
    }

    public void doPost(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
        this.performTask(var1, var2);
    }

}

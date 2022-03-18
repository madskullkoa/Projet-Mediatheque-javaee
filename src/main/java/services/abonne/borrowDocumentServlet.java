package services.abonne;

import com.mysql.jdbc.Util;
import mediatek2022.Document;
import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class borrowDocumentServlet extends HttpServlet {


    public borrowDocumentServlet() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        System.out.println("DANS LE doGet");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get session information
        HttpSession session = request.getSession();
        Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
        System.out.println(u);
        session.setAttribute("message", "");
        // get document registration form information
        String numeroDocument = request.getParameter("documentAEmprunter");
        System.out.println("DEBUG PRINT");
        System.out.println(numeroDocument);
        Document documentAEmprunter = Mediatheque.getInstance().getDocument(Integer.parseInt(numeroDocument));
        System.out.println("Document : "+ documentAEmprunter);
        try {
            Mediatheque.getInstance().emprunt(documentAEmprunter, u);
            session.setAttribute("message", "Document N°" + numeroDocument + " emprunté !");
            response.sendRedirect("subscriber/subscriber-view.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response); // il faut le mettre plus tôt
    }
}
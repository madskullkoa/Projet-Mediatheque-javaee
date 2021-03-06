package services.abonne;

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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Mediatheque m = Mediatheque.getInstance();
        // get session information
        HttpSession session = request.getSession();
        Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
        session.setAttribute("messageRetour", "");
        session.setAttribute("messageEmprunt", "");
        // get document registration form information
        String numeroDocument = request.getParameter("documentAEmprunter");

        try {
            Document documentAEmprunter = m.getDocument(Integer.parseInt(numeroDocument));
            m.emprunt(documentAEmprunter, u);
            session.setAttribute("messageEmprunt", "Document N°" + numeroDocument + " emprunté !");
        } catch (Exception e) {
            session.setAttribute("messageEmprunt", "Document N°" + numeroDocument + " indisponible !");
        }
        response.sendRedirect("subscriber/subscriber-view.jsp");
    }
}

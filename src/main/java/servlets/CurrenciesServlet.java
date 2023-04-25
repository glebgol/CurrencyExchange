package servlets;

import model.Currency;
import services.ICurrencyService;
import utils.IJsonMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@MultipartConfig
@WebServlet(name = "CurrenciesServlet", urlPatterns = "/currencies")
public class CurrenciesServlet extends HttpServlet {
    ICurrencyService currencyService;
    IJsonMapper mapper;

    @Override
    public void init(ServletConfig config) {
        mapper = (IJsonMapper) config.getServletContext().getAttribute("jsonMapper");
        currencyService = (ICurrencyService) config.getServletContext().getAttribute("currencyService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            List<Currency> currencies = currencyService.getAllCurrencies();
            String jsonCurrencies = mapper.map(currencies);

            resp.setStatus(200);
            resp.getWriter().write(jsonCurrencies);
        } catch (RuntimeException e) {
            resp.sendError(500, "Database is not available");
        }
    }
}

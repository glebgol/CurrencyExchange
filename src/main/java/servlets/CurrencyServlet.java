package servlets;

import dto.ErrorMessage;
import model.Currency;
import services.ICurrencyService;
import utils.IJsonMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "CurrencyServlet", value = "/currency/*")
public class CurrencyServlet extends HttpServlet {
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
            String currencyCode = req.getPathInfo().replaceFirst("/", "").toUpperCase();
            Optional<Currency> optionalCurrency = currencyService.getCurrencyByCode(currencyCode);
            if (optionalCurrency.isPresent()) {
                Currency currency = optionalCurrency.get();
                String jsonCurrency = mapper.map(currency);
                resp.getWriter().write(jsonCurrency);
                return;
            }
            ErrorMessage errorMessage = new ErrorMessage("Currency not found");
            String jsonErrorMessage = mapper.map(errorMessage);
            resp.sendError(404, jsonErrorMessage);
        } catch (RuntimeException e) {
            resp.sendError(500, "Database is not available");
        }
    }
}

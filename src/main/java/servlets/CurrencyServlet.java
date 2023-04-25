package servlets;

import dto.ErrorMessage;
import model.Currency;
import services.ICurrencyService;
import utils.ICurrencyRequestParser;
import utils.ICurrencyRequestValidator;
import utils.IJsonMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "CurrencyServlet", value = "/currency/*")
public class CurrencyServlet extends HttpServlet {
    IJsonMapper mapper;
    ICurrencyService currencyService;
    ICurrencyRequestValidator currencyRequestValidator;
    ICurrencyRequestParser currencyRequestParser;

    @Override
    public void init(ServletConfig config) {
        mapper = (IJsonMapper) config.getServletContext().getAttribute("jsonMapper");
        currencyService = (ICurrencyService) config.getServletContext().getAttribute("currencyService");
        currencyRequestValidator = (ICurrencyRequestValidator) config.getServletContext().getAttribute("currencyRequestValidator");
        currencyRequestParser = (ICurrencyRequestParser) config.getServletContext().getAttribute("currencyRequestParser");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String currencyCode = currencyRequestParser.getCurrencyCode(req);
            if (!currencyRequestValidator.isValidCurrencyCode(currencyCode)) {
                ErrorMessage errorMessage = new ErrorMessage("Bad request");
                String jsonErrorMessage = mapper.map(errorMessage);
                resp.sendError(400, jsonErrorMessage);
                return;
            }

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

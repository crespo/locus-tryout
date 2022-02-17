package software.locus.tryout.Service;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.locus.tryout.Model.Cidade;
import software.locus.tryout.Repository.CidadeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @PersistenceContext
    EntityManager em;

    public Cidade saveCidade(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    public List<Cidade> getCidades(Map<String, String> params) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cidade> cq = cb.createQuery(Cidade.class);
        Root<Cidade> root = cq.from(Cidade.class);

        Predicate predicate = cb.conjunction();
        List<Order> orders = new ArrayList<>();

        if (isValid(params.get("nome"))) {
            predicate.getExpressions().add(cb.like(root.get("nome"), "%" + params.get("nome") + "%"));
        }

        if (isInteger(params.get("populacao_min"))) {
            predicate.getExpressions().add(cb.gt(root.get("populacao"), Integer.parseInt(params.get("populacao_min"))));
        }

        if (isInteger(params.get("populacao_max"))) {
            predicate.getExpressions().add(cb.lt(root.get("populacao"), Integer.parseInt(params.get("populacao_max"))));
        }

        boolean order = false;
        boolean reverse = Objects.equals(params.get("ordernarReverso"), "True");

        if (Objects.equals(params.get("ordenarNome"), "True")) {
            if (!reverse) {
                orders.add(cb.asc(root.get("nome")));
            } else {
                orders.add(cb.desc(root.get("nome")));
            }
            order = true;
        }

        if (Objects.equals(params.get("ordenarPopulacao"), "True")) {
            if (!reverse) {
                orders.add(cb.asc(root.get("populacao")));
            } else {
                orders.add(cb.desc(root.get("populacao")));
            }
            order = true;
        }

        if (Objects.equals(params.get("ordenarEstado"), "True")) {
            if (!reverse) {
                orders.add(cb.asc(root.get("estado")));
            } else {
                orders.add(cb.desc(root.get("estado")));
            }
            order = true;
        }

        if (!order && !reverse) {
            orders.add(cb.asc(root.get("id")));
        } else if (!order) {
            System.out.println("teste");
            orders.add(cb.desc(root.get("id")));
        }

        cq.where(cb.and(predicate))
                .orderBy(orders)
                .select(root);

        Query<Cidade> query = (Query<Cidade>) em.createQuery(cq);
        List<Cidade> results = query.getResultList();

        return results;
    }

    public Cidade updateCidade(Cidade cidade) {
        Cidade existingCidade = cidadeRepository.findById(cidade.getId()).orElse(null);
        existingCidade.setNome(cidade.getNome());
        existingCidade.setPopulacao(cidade.getPopulacao());
        existingCidade.setEstado(cidade.getEstado());

        return cidadeRepository.save(existingCidade);
    }

    public String deleteCidade(int id) {
        cidadeRepository.deleteById(id);

        return "Estado " + id + " deleted.";
    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;

        // Not accepting negative numbers
        if (str.charAt(0) == '-') {
            return false;
        }

        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static boolean isValid(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        return length != 0;
    }
}

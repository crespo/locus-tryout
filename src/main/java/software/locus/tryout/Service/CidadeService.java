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

    public boolean saveCidade(Cidade cidade) {
        if (isInteger(cidade.getNome())) {
            return false;
        }
        cidadeRepository.save(cidade);
        return true;
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

        if (Objects.equals(params.get("ordenarNome"), "asc")) {
            orders.add(cb.asc(root.get("nome")));
        } else if (Objects.equals(params.get("ordenarNome"), "desc")) {
            orders.add(cb.desc(root.get("nome")));
        }

        if (Objects.equals(params.get("ordenarPopulacao"), "asc")) {
            orders.add(cb.asc(root.get("populacao")));
        } else if (Objects.equals(params.get("ordenarPopulacao"), "desc")) {
            orders.add(cb.desc(root.get("populacao")));
        }

        if (Objects.equals(params.get("ordenarEstado"), "asc")) {
            orders.add(cb.asc(root.get("estado")));
        } else if (Objects.equals(params.get("ordenarEstado"), "desc")) {
            orders.add(cb.desc(root.get("estado")));
        }

//        if (Objects.equals(params.get("ordenarReverso"), "True")) {
//            orders.add(cb.desc(root.get("id")));
//        }

        cq.where(cb.and(predicate))
                .orderBy(orders)
                .select(root);

        Query<Cidade> query = (Query<Cidade>) em.createQuery(cq);

        return query.getResultList();
    }

    public boolean updateCidade(Cidade cidade) {
        Cidade existingCidade = cidadeRepository
                                    .findById(cidade.getId())
                                    .orElse(null);

        if (existingCidade != null) {
            existingCidade.setNome(cidade.getNome());
            existingCidade.setPopulacao(cidade.getPopulacao());
            existingCidade.setEstado(cidade.getEstado());
            cidadeRepository.save(existingCidade);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteCidade(int id) {
        if (cidadeRepository.existsById(id)) {
            cidadeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
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

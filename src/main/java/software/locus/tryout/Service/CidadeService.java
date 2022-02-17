package software.locus.tryout.Service;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.locus.tryout.Model.Cidade;
import software.locus.tryout.Repository.CidadeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        CriteriaQuery<Cidade> cr = cb.createQuery(Cidade.class);
        Root<Cidade> root = cr.from(Cidade.class);
        cr.select(root);

        Query<Cidade> query = (Query<Cidade>) em.createQuery(cr);
        List<Cidade> results = query.getResultList();

        if (isValid(params.get("nome"))) {
            System.out.println("happens0");
        }

        if (isInteger(params.get("populacao_min"))) {
            System.out.println("happens1");
        }

        if (isInteger(params.get("populacao_max"))) {
            System.out.println("happens2");
        }

        if (Objects.equals(params.get("ordenacao"), "nome") ||
            Objects.equals(params.get("ordenacao"), "populacao") ||
            Objects.equals(params.get("ordenacao"), "estado")) {
            System.out.println("happens3");
        }

        if (Objects.equals(params.get("ordemreversa"), "True")) {
            System.out.println("happens4");
        }

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

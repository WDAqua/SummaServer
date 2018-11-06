package edu.kit.aifb.summarizer.implemented;

import org.springframework.stereotype.Component;

import edu.kit.aifb.summarizer.Summarizer;

/**
 * This is an example summarization approach that generates summaries with
 * the public DBpedia SPARQL endpoint.
 *
 */
@Component
public class DBpedia extends Summarizer {

	public String getName() {
		return "dbpedia";
	}

	public String getRepository(){
		return "https://qanswer-hdt-endpoint.univ-st-etienne.fr/dbpedia_2016_10/sparql";
	}

	public String getQuery0(){
		return "SELECT DISTINCT ?l WHERE { "
				+ "OPTIONAL {<ENTITY> <http://www.w3.org/2000/01/rdf-schema#label> ?l . "
				+ "FILTER regex(lang(?l), \"LANG\", \"i\") . }}";
	}

	public String getQuery1(){
		return "PREFIX vrank:<http://purl.org/voc/vrank#> " +
				"SELECT DISTINCT ?o ?l ?pageRank " +
				"WHERE { " +
				"<ENTITY> ?p ?o . " +
				"PREDICATES " +
				"FILTER (?p != <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> && " +
				" ?p != <http://purl.org/dc/terms/subject> && " +
				" ?p != <http://www.w3.org/2002/07/owl#sameAs> && " +
				" ?p != <http://www.w3.org/2000/01/rdf-schema#seeAlso> &&" +
				" ?p != <http://purl.org/linguistics/gold/hypernym> &&" +
				" ?p != <http://dbpedia.org/ontology/wikiPageDisambiguates> ) " +
				"?o <http://www.w3.org/2000/01/rdf-schema#label> ?l . " +
				"FILTER STRENDS(lang(?l), \"LANG\") . " +
				"graph <http://dbpedia.com/pageRank> { " +
				"?o <http://purl.org/voc/vrank#pagerank> ?pageRank . " +
				"}} " +
				"ORDER BY DESC (?pageRank) LIMIT TOPK ";
	}

	public String getQuery1b(){
		return "PREFIX vrank:<http://purl.org/voc/vrank#> " +
				"SELECT DISTINCT ?o ?l ?pageRank " +
				"WHERE { " +
				"?o ?p <ENTITY> . " +
				"PREDICATES " +
				"FILTER (?p != <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> &&" +
				" ?p != <http://purl.org/dc/terms/subject> && " +
				" ?p != <http://www.w3.org/2002/07/owl#sameAs> && " +
				" ?p != <http://www.w3.org/2000/01/rdf-schema#seeAlso> &&" +
				" ?p != <http://purl.org/linguistics/gold/hypernym> &&" +
				" ?p != <http://dbpedia.org/ontology/wikiPageDisambiguates> ) " +
				"?o <http://www.w3.org/2000/01/rdf-schema#label> ?l . " +
				"FILTER STRENDS(lang(?l), \"LANG\") . " +
				"graph <http://dbpedia.com/pageRank> { " +
				"?o <http://purl.org/voc/vrank#pagerank> ?pageRank . " +
				"}} " +
				"ORDER BY DESC (?pageRank) LIMIT TOPK ";
	}

	public String getQuery2() {
		return "PREFIX vrank:<http://purl.org/voc/vrank#>"
				+ "SELECT ?p ?l "
				+ "WHERE {"
				+ "<ENTITY> ?p <OBJECT> . "
				+ "OPTIONAL { "
				+ "  ?p <http://www.w3.org/2000/01/rdf-schema#label> ?l . "
				+ "FILTER regex(lang(?l), \"LANG\", \"i\")} } ORDER BY asc(?p) limit 1 ";
	}

	public String getQuery2b(){
		return "PREFIX vrank:<http://purl.org/voc/vrank#>"
				+ "SELECT ?p ?l "
				+ "WHERE {"
				+ "<OBJECT> ?p <ENTITY> . "
				+ "OPTIONAL { "
				+ "  ?o <http://wikiba.se/ontology-beta#directClaim> ?p . "
				+ "  ?o <http://www.w3.org/2000/01/rdf-schema#label> ?l . "
				+ "FILTER regex(lang(?l), \"LANG\", \"i\")} } ORDER BY asc(?p) limit 1 ";
	}

}

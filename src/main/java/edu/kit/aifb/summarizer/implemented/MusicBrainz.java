package edu.kit.aifb.summarizer.implemented;

import org.springframework.stereotype.Component;

import edu.kit.aifb.summarizer.Summarizer;

@Component
public class MusicBrainz extends Summarizer {

	public String getName() {
		return "musicbrainz";
	}

	public String getRepository(){
		return "http://qanswer-hdt-endpoint.univ-st-etienne.fr/musicbrainz/sparql";
	}

	public String getQuery0(){
		return "SELECT DISTINCT ?l WHERE { "
				+ "OPTIONAL {<ENTITY> <http://www.w3.org/2000/01/rdf-schema#label> ?l . }"
				+ "OPTIONAL {<ENTITY> <http://xmlns.com/foaf/0.1/name> ?l } "
				+ "OPTIONAL {<ENTITY> <http://purl.org/dc/elements/1.1/title> ?l }}";
	}

	public String getQuery1(){
		return "PREFIX vrank:<http://purl.org/voc/vrank#> "
                        + "SELECT ?o ?l ?pageRank WHERE { "
                        + "<ENTITY> ?p ?o . "
						+ " graph <http://pageRank.com> { "
						+ "   ?o <http://purl.org/voc/vrank#pagerank> ?pageRank. "
                        + "} "
                        + "OPTIONAL {?o <http://www.w3.org/2000/01/rdf-schema#label> ?l . } "
                        + "OPTIONAL {?o <http://xmlns.com/foaf/0.1/name> ?l } "
                        + "OPTIONAL {?o <http://purl.org/dc/elements/1.1/title> ?l } "
                        + "FILTER (lang(?l)=\"en\" || lang(?l)=\"\"). "
                        + "} ORDER BY DESC (?pageRank) LIMIT TOPK ";
	}

	public String getQuery1b(){
		return "PREFIX vrank:<http://purl.org/voc/vrank#> "
				+ "SELECT ?o ?l ?pageRank WHERE { "
				+ "<ENTITY> ?p ?o . "
				+ " graph <http://pageRank.com> { "
				+ "   ?o <http://purl.org/voc/vrank#pagerank> ?pageRank. "
				+ "} "
				+ "OPTIONAL {?o <http://www.w3.org/2000/01/rdf-schema#label> ?l . } "
				+ "OPTIONAL {?o <http://xmlns.com/foaf/0.1/name> ?l } "
				+ "OPTIONAL {?o <http://purl.org/dc/elements/1.1/title> ?l } "
				+ "FILTER (lang(?l)=\"en\" || lang(?l)=\"\"). "
				+ "} ORDER BY DESC (?pageRank) LIMIT TOPK ";
	}

	public String getQuery2() {
		return "PREFIX vrank:<http://purl.org/voc/vrank#>"
				+ "SELECT ?p ?l ?rank "
				+ "WHERE { "
				+ "<ENTITY> ?p <OBJECT> . "
				+ "OPTIONAL {?p <http://www.w3.org/2000/01/rdf-schema#label> ?l. } "
				+ "} ORDER BY asc(?p)";
	}

	public String getQuery2b(){
		return "PREFIX vrank:<http://purl.org/voc/vrank#>"
				+ "SELECT ?p ?l ?rank "
				+ "WHERE { "
				+ "<OBJECT> ?p <ENTITY> . "
				+ "OPTIONAL {?p <http://www.w3.org/2000/01/rdf-schema#label> ?l. } "
				+ "} ORDER BY asc(?p)";
	}

}

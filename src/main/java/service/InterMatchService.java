package service;

import beans.Match;

public interface InterMatchService {
    Match startMatchAndGetWinner(String a, String b);
}

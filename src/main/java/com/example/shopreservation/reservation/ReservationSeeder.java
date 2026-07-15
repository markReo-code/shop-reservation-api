package com.example.shopreservation.reservation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class ReservationSeeder implements CommandLineRunner {
    private final ReservationRepository reservationRepository;

    public ReservationSeeder(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(String... args) {
        if (reservationRepository.count() > 0) {
            System.out.println(
                "予約データが存在するため、シード処理をスキップしました。"
            );
            return;
        }

        reservationRepository.saveAll(List.of(
            new Reservation(
                "田中太郎",
                "taro.tanaka@example.com",
                "渋谷店",
                LocalDateTime.of(2026, 7, 20, 11, 0),
                ReservationStatus.CONFIRMED
            ),
            new Reservation(
                "山田花子",
                "hanako.yamada@example.com",
                "新宿店",
                LocalDateTime.of(2026, 7, 21, 14, 30),
                ReservationStatus.PENDING
            ),
            new Reservation(
                "佐藤健一",
                "kenichi.sato@example.com",
                "銀座店",
                LocalDateTime.of(2026, 7, 22, 19, 0),
                ReservationStatus.CONFIRMED
            ),
            new Reservation(
                "鈴木美咲",
                "misaki.suzuki@example.com",
                "表参道店",
                LocalDateTime.of(2026, 7, 23, 16, 0),
                ReservationStatus.CANCELED
            ),
            new Reservation(
                "高橋翔",
                "sho.takahashi@example.com",
                "池袋店",
                LocalDateTime.of(2026, 7, 24, 10, 30),
                ReservationStatus.CONFIRMED
            ),
            new Reservation(
                "伊藤優子",
                "yuko.ito@example.com",
                "横浜店",
                LocalDateTime.of(2026, 7, 25, 18, 30),
                ReservationStatus.PENDING
            ),
            new Reservation(
                "渡辺大輔",
                "daisuke.watanabe@example.com",
                "渋谷店",
                LocalDateTime.of(2026, 7, 26, 9, 0),
                ReservationStatus.CONFIRMED
            ),
            new Reservation(
                "小林彩",
                "aya.kobayashi@example.com",
                "新宿店",
                LocalDateTime.of(2026, 7, 27, 13, 0),
                ReservationStatus.CANCELED
            ),
            new Reservation(
                "加藤直樹",
                "naoki.kato@example.com",
                "銀座店",
                LocalDateTime.of(2026, 7, 28, 20, 0),
                ReservationStatus.PENDING
            ),
            new Reservation(
                "吉田真由",
                "mayu.yoshida@example.com",
                "表参道店",
                LocalDateTime.of(2026, 7, 29, 15, 30),
                ReservationStatus.CONFIRMED
            ),
            new Reservation(
                "中村拓也",
                "takuya.nakamura@example.com",
                "池袋店",
                LocalDateTime.of(2026, 7, 10, 12, 0),
                ReservationStatus.COMPLETED
            ),
            new Reservation(
                "松本理奈",
                "rina.matsumoto@example.com",
                "横浜店",
                LocalDateTime.of(2026, 7, 12, 17, 30),
                ReservationStatus.COMPLETED
            )
        ));

        System.out.println("予約シードデータを12件登録しました。");
    }
}

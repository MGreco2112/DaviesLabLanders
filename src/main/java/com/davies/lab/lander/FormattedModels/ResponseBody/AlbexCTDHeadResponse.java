package com.davies.lab.lander.FormattedModels.ResponseBody;

import com.davies.lab.lander.Models.ProcessedAlbexCTDData;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class AlbexCTDHeadResponse {
    private Long HeadID;
    private String LanderID;
    private Set<AlbexCTDDataResponse> data = new HashSet<>();

    public AlbexCTDHeadResponse(Long headID, String landerID) {
        HeadID = headID;
        LanderID = landerID;
    }

    public void createDataResponse(ProcessedAlbexCTDData AlbexData) {
        AlbexCTDDataResponse res = new AlbexCTDDataResponse(AlbexData.getID(), AlbexData.getDate());
        data.add(res);
    }
    private class AlbexCTDDataResponse {
        private Long ID;
        private LocalDateTime Date;

        public AlbexCTDDataResponse(Long ID, LocalDateTime date) {
            this.ID = ID;
            Date = date;
        }

        public Long getID() {
            return ID;
        }

        public void setID(Long ID) {
            this.ID = ID;
        }

        public LocalDateTime getDate() {
            return Date;
        }

        public void setDate(LocalDateTime date) {
            Date = date;
        }
    }
}

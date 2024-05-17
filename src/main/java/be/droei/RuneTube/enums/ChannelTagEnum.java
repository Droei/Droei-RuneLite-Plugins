package be.droei.RuneTube.enums;

public enum ChannelTagEnum {
        PROGRESS(0),
        VARIETY(1),
        PVP(2);

        private int number;
        ChannelTagEnum(int number) {
                this.number = number;
        }

        public int getNumber(){
                return number;
        }
}

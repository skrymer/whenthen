package org.skrymer.whenthen;

import java.util.Optional;
import java.util.function.Supplier;

public final class WhenThen {

    private WhenThen(){}

    public static Then when(final boolean whenTrue){
        return whenTrue ? Then.of(true) : Then.of(false);
    }

    public static Then unless(final boolean unlessTrue){
        return unlessTrue ? Then.of(false) : Then.of(true);
    }

    public static final class Then {
        private boolean doRun;

        private static Then of(boolean doRun){
            return new Then(doRun);
        }

        private Then(boolean doRun) {
            this.doRun = doRun;
        }

        public void then(Procedure run){
            if(doRun) {
                run.run();
            }
        }

        public <T extends RuntimeException> void thenThrow(Supplier<T> e) throws RuntimeException {
            if(doRun) {
                throw e.get();
            }
        }

        public <T> Optional<T> thenReturn(Supplier<T> supplier){
            return (doRun) ? Optional.ofNullable(supplier.get()) : Optional.empty();
        }

        public void thenThrowIllegalArgument(String argName)  {
            if(doRun) {
                throw new IllegalArgumentException("Illegal argument: " + argName);
            }
        }

        public Then and(boolean isTrue){
            if(doRun){
                doRun = isTrue;
            }
            return this;
        }

        public Then and(Supplier<Boolean> whenTrue){
            if(doRun){
                doRun = whenTrue.get();
            }
            return this;
        }
    }
}


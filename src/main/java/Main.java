import com.badlogic.gdx.math.RandomXS128;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: seed [a#|n1 n2 ... n16]");
            System.exit(1);
        }

        // Parse command-line arguments
        long seed = Long.parseLong(args[0]);
        String[] a = new String[args.length - 1];
        System.arraycopy(args, 1, a, 0, a.length);

        // Initialize RNG
        RandomXS128 rng = new RandomXS128();
        rng.setSeed(seed);

        // Print seed
        System.out.println("RngValues {");
        System.out.println("    seed: " + seed + "i64 as u64,");

        // Print values
        System.out.print("    values: [");
        for (int i = 0; i < a.length; i++) {
            if (a[i].startsWith("a")) {
                System.out.print("Some(RngValue::Advance(" + a[i].substring(1) + ")), ");
                for (int j = 0; j < Long.parseLong(a[i].substring(1)); j++) {
                    rng.nextLong();
                }
            } else {
                long ai = Long.parseLong(a[i]);
                if (ai > 0) {
                    long result = rng.nextLong(ai);
                    System.out.print("Some(RngValue::CappedU64 { modulus: " + ai + ", residue: " + result + " }), ");
                } else {
                    System.out.print("Some(RngValue::U64(" + rng.nextLong() + "i64 as u64)), ");
                }
            }
        }

        // Fill rest of the array with None
        for (int i = a.length; i < 16; i++) {
            System.out.print("None, ");
        }

        System.out.println("],");
        System.out.println("}");
    }
}

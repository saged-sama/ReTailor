import { PUBLIC_API_URL } from "$env/static/public";
import SpringBase from "./springbase/springbase";

export const springbase = new SpringBase(PUBLIC_API_URL);